package com.excel.myapplication.repo

import com.excel.myapplication.utils.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
}