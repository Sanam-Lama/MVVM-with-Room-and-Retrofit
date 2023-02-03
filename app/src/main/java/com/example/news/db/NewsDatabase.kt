package com.example.news.db

import android.content.Context
import androidx.room.*
import com.example.news.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    //will return an object of NewsDao
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null  //holds the database

        /**
         * function to return the database
         * - check if the instance of database is null or not
         * - if null, it will create it
         * - if not null, then it will return whatever the database is or has
         */
        fun getDatabase(context: Context): NewsDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                        NewsDatabase::class.java,
                    "articleDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}