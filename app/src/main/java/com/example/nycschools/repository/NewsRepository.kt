package com.example.nycschools.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nycschools.api.NewsService
import com.example.nycschools.models.Article
import com.example.nycschools.models.News
import com.example.nycschools.models.Source

/**
 * If we are doing a network call then we add a parameter for that netwrok service and
 * if we are accessing db then we add a parameter for db
 * if we have both then we add both as a parameter
 *
 * the functions defined here in repository are called through the interface (NewsService) and vice versa
 * and the view models access these functions here and get the data
 */

class NewsRepository(private val newsService: NewsService) {

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
    suspend fun getNews(country: String, page: Int) {
        // assigning the result that we get from network call to a variable
        val result = newsService.getNews(country, page)
        if (result?.body() != null) {   //checking if result and the result.body() are null or not
            newsLiveData.postValue(result.body())
        }
    }
}