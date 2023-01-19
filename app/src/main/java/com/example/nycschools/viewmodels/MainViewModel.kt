package com.example.nycschools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.repository.SchoolRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * MainViewModel class here is added to communicated between the data and the model that we have
 * added sepaaretly in the project. This class will makes a call to repository and from there we
 * get the observed data and is sent to the view.
 */

class MainViewModel(private val repository: SchoolRepository) : ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSchools()
        }
    }

    val schools : LiveData<String>
    get() = repository.schools
}