package com.example.nycschools.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nycschools.models.SchoolList
import com.example.nycschools.models.SchoolListItem

/**
 * this is a Data Access Object class which is an interface and we add the
 * CRUD operations here to insert, read, update, and delete the items from
 * the database.
 * the main purpose of this class is to help us access our database.
 */

@Dao
interface SchoolDao {

    @Insert
    suspend fun addSchool(schools: SchoolListItem?)

    @Query("SELECT * FROM schools")
     fun getSchools() : LiveData<SchoolListItem>
}