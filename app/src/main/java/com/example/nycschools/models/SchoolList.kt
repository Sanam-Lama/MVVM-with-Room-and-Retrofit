package com.example.nycschools.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This is a model class for the data that we will parse from the api call
 * I have added only the necessary variable i.e. school_name
 */

//@Entity(tableName = "schools")
//data class SchoolList(
//    @PrimaryKey(autoGenerate = true)
//    var schoolId: Int? = null,
//    val school_name: String
//)

@Entity(tableName = "schools")
data class SchoolList (
    @PrimaryKey(autoGenerate = true)
    var schoolId: Int? = null,
    val schools: List<SchoolListItem>)