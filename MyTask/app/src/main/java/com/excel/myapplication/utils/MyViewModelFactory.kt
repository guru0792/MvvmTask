package com.excel.myapplication.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.excel.myapplication.repo.MainRepository
import com.excel.myapplication.room.Repository
import com.excel.myapplication.ui.MainViewModel

class MyViewModelFactory constructor(private val repository: MainRepository, private val repository2: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository, this.repository2) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}