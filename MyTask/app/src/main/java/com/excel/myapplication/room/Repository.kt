package com.excel.myapplication.room


import androidx.lifecycle.LiveData

import android.app.Application
import com.excel.myapplication.model.ResponseModel


class Repository(application: Application?): RepoDataUser {
    private val database: MyDatabase = MyDatabase.getInstance(application)!!
    override  fun getData(): LiveData<List<ResponseModel>> {
        return database.catimgDao().getcats()
    }

    override suspend fun inserData(cats: List<ResponseModel?>?) {
        database.catimgDao().insert(cats)
    }

    override suspend fun deleteData(id: String) {
        database.catimgDao().deleteItem(id)
    }

    override suspend fun updateData(cats: List<ResponseModel?>?) {
        database.catimgDao().update(cats)
    }


}

interface RepoDataUser{
    fun getData(): LiveData<List<ResponseModel>>
    suspend fun inserData(cats: List<ResponseModel?>?)
    suspend fun deleteData(id: String)
    suspend fun updateData(cats: List<ResponseModel?>?)
}