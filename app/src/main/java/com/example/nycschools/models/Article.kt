package com.example.nycschools.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * we will be storing following information in our database
 *
 * object source (val source: Source) returns a Source type which needs to be converted to something that
 * room database understands, e.g. int, string, etc
 * so, we create a TypeConverter class for the source object
 */

@Entity(tableName = "article")
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)