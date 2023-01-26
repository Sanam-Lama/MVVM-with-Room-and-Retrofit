package com.example.nycschools.api

import com.example.nycschools.models.SchoolList
import com.example.nycschools.models.SchoolListItem
import retrofit2.Response
import retrofit2.http.GET

//https://data.cityofnewyork.us/Education/2017-DOE-High-School-Directory/s3k6-pzi2

/**
 * this is the interface where we define the endpoints. since I was supposed to get the data from the
 * given api call, I used GET method to retrieve the data from the api.
 * https://data.cityofnewyork.us/Education/2017-DOE-High-School-Directory/s3k6-pzi2
 */

interface SchoolService {

    @GET("Education/2017-DOE-High-School-Directory/s3k6-pzi2.json")
    suspend fun getSchools() : Response<SchoolListItem>
}