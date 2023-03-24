package com.example.news

import android.app.Application
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.news.api.NewsService
import com.example.news.api.RetrofitHelper
import com.example.news.db.NewsDatabase
import com.example.news.repository.NewsRepository
import com.example.news.worker.NewsWorker
import java.util.concurrent.TimeUnit

/**
 * This class is created to create a reference of repository so that all other viewmodels can
 * access it
 */
class NewsApplication : Application() {

    lateinit var newsRepository: NewsRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    /**
     * we need a constraint to check if we have internet connection or not
     * here we are sending a Periodic work request and asking to load doWork() method in
     * NewsWorker class after each 15 minutes
     * we enqueue the workRequest we created in WorkManager
     */
    private fun setUpWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequest.Builder(NewsWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        val newsDatabase = NewsDatabase.getDatabase(applicationContext)
        newsRepository = NewsRepository(newsService, newsDatabase, applicationContext)
    }
}