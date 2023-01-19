package com.example.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ccom.example.nycschools.db.SchoolDatabase
import com.example.nycschools.adapters.MyAdapter
import com.example.nycschools.api.RetrofitHelper
import com.example.nycschools.api.SchoolService
import com.example.nycschools.repository.SchoolRepository
import com.example.nycschools.viewmodels.MainViewModel
import com.example.nycschools.viewmodels.MainViewModelFactory

/**
 * MainActivity is the view in this whole project and holds the observers that will update the
 * UI with datas that we receive from viewmodel. The observables in ViewModel are observed here
 * and the update is automatically updated in the view here.
 */

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val schoolService = RetrofitHelper.getInstance().create(SchoolService::class.java)
        val database = SchoolDatabase.getDatabase(this)
        val repository = SchoolRepository(schoolService, database)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.schools.observe(this) {
            adapter = MyAdapter(this, it)
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}

