package com.example.news.api

import com.example.news.models.News
//import com.example.nycschools.models.Source
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface to define the endpoints
 * Api : https://newsapi.org/v2/top-headlines?apiKey=14b6ac08c47444af8dd9fbc57f82e6d3&country=us&page=1
 * we will perform our GET, POST, UPDATE, DELETE operations here and add parameters, headers and body
 * as per the requirement
 */

const val API_KEY = "14b6ac08c47444af8dd9fbc57f82e6d3"
interface NewsService {

    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun getNews(@Query("country") country: String, @Query("page") page: Int) : Response<News>
}