package com.designloft.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PhotoProductEntity")
data class PhotoProductEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "category_id")
    var categoryId: Int = 0,

    @ColumnInfo(name = "product_id")
    var productId: Int = 0,

    @ColumnInfo(name = "image_link")
    var imageLink: String = ""

)

