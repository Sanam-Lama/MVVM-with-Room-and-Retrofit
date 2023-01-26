package com.example.nycschools.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nycschools.models.Article

@Dao
interface NewsDao {

    @Insert
    suspend fun addArticles(article: List<Article>)

    @Query("SELECT * FROM article")
    fun getArticles() : List<Article>
}