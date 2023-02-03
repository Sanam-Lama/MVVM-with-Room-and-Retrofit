package com.example.news

import android.app.Application
import com.example.news.api.NewsService
import com.example.news.api.RetrofitHelper
import com.example.news.db.NewsDatabase
import com.example.news.repository.NewsRepository

/**
 * This class is created to create a reference of repository so that all other viewmodels can
 * access it
 */
class NewsApplication : Application() {

    lateinit var newsRepository: NewsRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        val newsDatabase = NewsDatabase.getDatabase(applicationContext)
        newsRepository = NewsRepository(newsService, newsDatabase, applicationContext)
    }
}