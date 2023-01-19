package com.example.nycschools.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ccom.example.nycschools.db.SchoolDatabase
import com.example.nycschools.api.SchoolService

/**
 * This is the repository class for this project where we add the methods for network calls.
 * We make use of LiveData and MutableLiveData to helps us observe the actual data that is
 * coming from the network.
 *
 * Since, I tried to store the data in local database for offline stages, I have added the
 * call o database to add the data to it.
 *
 * ViewModel will call the methods in here and get the required data from the call
 */

class SchoolRepository(private val schoolService: SchoolService, private val schoolDatabase: SchoolDatabase) {

    private val schoolLiveData = MutableLiveData<String>()

    val schools: LiveData<String>
    get() = schoolLiveData

    suspend fun getSchools() {
        val result = schoolService.getSchools()
        if (result?.body() != null) {
            schoolDatabase.schoolDao().addSchool(result.body())
            schoolLiveData.postValue(result.body())
        }
    }
}