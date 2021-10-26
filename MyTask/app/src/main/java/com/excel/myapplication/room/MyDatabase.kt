package com.excel.myapplication.room

import android.content.Context
import android.os.AsyncTask

import androidx.sqlite.db.SupportSQLiteDatabase

import androidx.room.Room

import androidx.room.RoomDatabase


import androidx.room.Database
import com.excel.myapplication.model.ResponseModel


@Database(entities = [ResponseModel::class], version = 1,exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun catimgDao(): MyDAO
    companion object {
        private const val DATABASE_NAME = "Cat"
        @Volatile
        var INSTANCE: MyDatabase? = null
        fun getInstance(context: Context?): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = context?.let {
                            Room.databaseBuilder(
                                it,
                                MyDatabase::class.java, DATABASE_NAME
                            ).build()
                        }
                    }
                }
            }
            return INSTANCE
        }


    }
}