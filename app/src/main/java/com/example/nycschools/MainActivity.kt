package com.example.nycschools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.adapters.MyAdapter
import com.example.nycschools.api.NewsService
import com.example.nycschools.api.RetrofitHelper
import com.example.nycschools.repository.NewsRepository
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
        recyclerView = findViewById(R.id.list_of_school)

//        val schoolService = RetrofitHelper.getInstance().create(SchoolService::class.java)
//        val database = SchoolDatabase.getDatabase(this)
//        val repository = SchoolRepository(schoolService, database)

        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        val newsRepository = NewsRepository(newsService)

        //initializing viewmodel
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(newsRepository))[MainViewModel::class.java]
//        mainViewModel.schools.observe(this) {
        mainViewModel.news.observe(this) {
            Log.d("MAINACTIVITY, NEWS", it.articles.toString())
            adapter = MyAdapter(this, it.articles)
//            adapter = MyAdapter(this, it.articles[0].source.name)
            recyclerView.adapter = adapter
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.layoutManager = LinearLayoutManager(this)
            adapter.notifyDataSetChanged()

            adapter.onItemClick = {
                val intent = Intent(this, NewsActivity::class.java)
                intent.putExtra("DESCRIPTION", it.description)
                startActivity(intent)
            }
        }
    }
}

