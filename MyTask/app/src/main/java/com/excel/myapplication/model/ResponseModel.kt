package com.excel.myapplication.model

import androidx.room.ColumnInfo

import androidx.room.Entity
import androidx.room.Index

import androidx.room.PrimaryKey






//data class ResponseModel(val alert_message: String, val alert_location : String, val alert_priority: String, val alert_module_type: String)

@Entity(tableName = "catimg", indices = arrayOf(Index(value = ["alert_id"], unique = true)))
class ResponseModel {
    @PrimaryKey
    @ColumnInfo(name = "alert_id")
    var alert_id: String = null.toString()


    @ColumnInfo(name = "alert_message")
    var alert_message: String? = null

    @ColumnInfo(name = "alert_location")
    var alert_location: String? = null

    @ColumnInfo(name = "alert_priority")
    var alert_priority: String? = null

    @ColumnInfo(name = "alert_module_type")
    var alert_module_type: String? = null
}


