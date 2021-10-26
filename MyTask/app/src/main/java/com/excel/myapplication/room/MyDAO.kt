package com.excel.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.*

import com.excel.myapplication.model.ResponseModel


@Dao
interface MyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(cats: List<ResponseModel?>?)

    @Query("SELECT DISTINCT * FROM catimg")
     fun getcats(): LiveData<List<ResponseModel>>

    @Query("DELETE FROM catimg WHERE alert_id = :alert_id")
    fun deleteItem(alert_id: String)

    @Query("DELETE FROM catimg")
    suspend fun feleteAllData()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(cats: List<ResponseModel?>?)
}