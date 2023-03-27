package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.news.adapters.MyAdapter
import com.example.news.adapters.NewsPagingAdapter
import com.example.news.api.NewsService
import com.example.news.api.RetrofitHelper
import com.example.news.repository.NewsPagingRepository
import com.example.news.viewmodels.MainViewModel
import com.example.news.viewmodels.MainViewModelFactory
import com.example.news.viewmodels.PagingViewModel
import com.example.news.viewmodels.PagingViewModelFactory

/**
 * MainActivity is the view in this whole project and holds the observers that will update the
 * UI with datas that we receive from viewmodel. The observables in ViewModel are observed here
 * and the update is automatically updated in the view here.
 */

class MainActivity : AppCompatActivity() {

//    lateinit var mainViewModel: MainViewModel
//    lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView

    //for paging
    lateinit var pagingAdapter: NewsPagingAdapter
    lateinit var pagingViewModel: PagingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.list_of_school)

        //accessing repository
        val newsRepository = (application as NewsApplication).newsRepository

        //for regular recyclerview
        //initializing viewmodel
//        mainViewModel = ViewModelProvider(this, MainViewModelFactory(newsRepository))[MainViewModel::class.java]
//        mainViewModel.news.observe(this) {
//            Toast.makeText(this, it.articles.size.toString(), Toast.LENGTH_SHORT).show()
//            Log.d("MAINACTIVITY, NEWS", it.articles.toString())
//            adapter = MyAdapter(this, it.articles)
//            recyclerView.adapter = adapter
//            recyclerView.itemAnimator = DefaultItemAnimator()
//            recyclerView.layoutManager = LinearLayoutManager(this)
//            adapter.notifyDataSetChanged()
//
//            adapter.onItemClick = {
//                val intent = Intent(this, NewsActivity::class.java)
//                intent.putExtra("DESCRIPTION", it.description)
//                startActivity(intent)
//            }
//        }


        //for Paging
        val service = RetrofitHelper.getInstance().create(NewsService::class.java)
        val repository = NewsPagingRepository(service)
        pagingAdapter = NewsPagingAdapter()
        pagingViewModel = ViewModelProvider(this, PagingViewModelFactory(repository))[PagingViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = pagingAdapter

        pagingViewModel.list.observe(this, Observer { pagingData ->
            pagingAdapter.submitData(lifecycle, pagingData)
        })

    }
}

