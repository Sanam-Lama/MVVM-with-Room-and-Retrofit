package com.example.nycschools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.models.Article
import com.example.nycschools.models.News
//import com.example.nycschools.models.Source
import com.example.nycschools.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * MainViewModel class here is added to communicated between the data and the model that we have
 * added sepaaretly in the project. This class will makes a call to repository and from there we
 * get the observed data and is sent to the view.
 */

//class MainViewModel(private val repository: SchoolRepository) : ViewModel(){
class MainViewModel(private val repository: NewsRepository) : ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNews("us", 1)
        }
    }

    //assigning the value of LiveData from repository to viewmodel so that this LiveData can
    //be accessed from the view
    val news : LiveData<News>
    get() = repository._newsLiveData


    /**
     *  init {
    viewModelScope.launch(Dispatchers.IO) {
    repository.getSchools()
    }
    }

    val schools : LiveData<String>
    get() = repository.schools
     */

}