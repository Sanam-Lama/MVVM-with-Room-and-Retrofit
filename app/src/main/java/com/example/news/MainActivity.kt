package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.adapters.MyAdapter
import com.example.news.viewmodels.MainViewModel
import com.example.news.viewmodels.MainViewModelFactory

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

        //accessing repository
        val newsRepository = (application as NewsApplication).newsRepository

        //initializing viewmodel
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(newsRepository))[MainViewModel::class.java]
//        mainViewModel.schools.observe(this) {
        mainViewModel.news.observe(this) {
            Toast.makeText(this, it.articles.size.toString(), Toast.LENGTH_SHORT).show()
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

