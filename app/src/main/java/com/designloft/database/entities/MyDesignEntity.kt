package com.designloft.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyDesignEntity")
class MyDesignEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "image")
    var image: String = ""

)