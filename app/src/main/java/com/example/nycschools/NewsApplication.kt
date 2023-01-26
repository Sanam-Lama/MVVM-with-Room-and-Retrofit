package com.example.nycschools

import android.app.Application
import com.example.nycschools.api.NewsService
import com.example.nycschools.api.RetrofitHelper
import com.example.nycschools.db.NewsDatabase
import com.example.nycschools.repository.NewsRepository

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