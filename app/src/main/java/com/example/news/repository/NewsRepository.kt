package com.example.news.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.api.NewsService
import com.example.news.db.NewsDatabase
import com.example.news.models.News
import com.example.news.utils.NetworkUtils

//import com.example.nycschools.models.Source

/**
 * If we are doing a network call then we add a parameter for that netwrok service and
 * if we are accessing db then we add a parameter for db
 * if we have both then we add both as a parameter
 *
 * the functions defined here in repository are called through the interface (NewsService) and vice versa
 * and the view models access these functions here and get the data
 */

class NewsRepository(
    private val newsService: NewsService,
    private val newsDatabase: NewsDatabase,
    private val context: Context) {

    /**this variable will hold the news data coming from news api
     * making this variable private so that the external classes cannot access or modify them
     * cause MutableLiveData are modifiable
     *
     * LiveData are not modifiable and should be publically accessible since view will observe them
     * using observer through view model
     */
    private val newsLiveData = MutableLiveData<News>()
    val _newsLiveData: LiveData<News>
        get() = newsLiveData

    //this is the function that viewmodel will call when they need data from the api
    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getNews(country: String, page: Int) {

        if (NetworkUtils.isOnline(context)) {
            // assigning the response to result variable that we get from network call
            val result = newsService.getNews(country, page)
            if (result?.body() != null) {   //checking if result and the result.body() are null or not

                newsDatabase.newsDao().addArticles(result.body()!!.articles)    //adding response to the database
                newsLiveData.postValue(result.body())   //adding response from api to livedata (mutableLiveData)
            }
        } else {
            //access it from database
            val news = newsDatabase.newsDao().getArticles()
            val newsList = News(news, "", 1)
            newsLiveData.postValue(newsList)
        }
    }

    /**
     * will run the api in background and will save the data in our database
     */
    suspend fun getNewsInBackground(country: String) {
        //creating randomPageNumber from where we can fetch the news
        val randomPageNumber = (Math.random() * 10).toInt()
        val result = newsService.getNews(country, randomPageNumber)
        if (result?.body() != null) {
            newsDatabase.newsDao().addArticles(result.body()!!.articles)
        }
    }
}