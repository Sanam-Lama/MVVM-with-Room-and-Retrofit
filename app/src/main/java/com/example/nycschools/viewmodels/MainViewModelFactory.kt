package com.example.nycschools.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nycschools.repository.NewsRepository

/**
 * Whenever we have a parameterized ViewModel, we need to create a ViewModelFactory
 */
//class MainViewModelFactory(private val repository: SchoolRepository) : ViewModelProvider.Factory {
class MainViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}