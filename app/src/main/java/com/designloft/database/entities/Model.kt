package com.designloft.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Model")
class Model (@PrimaryKey(autoGenerate = true)
             val id: Int?,

             @ColumnInfo(name = "type")
             val type: Int,

             @ColumnInfo(name = "name")
             var name: String,

             @ColumnInfo(name = "image")
             var image: Int? = null,

             @ColumnInfo(name = "vrImage")
             var vrImage: String? = null,

             @ColumnInfo(name = "parentFolderId")
             var parentFolderId: Int? = null){

    companion object {
        val TEXT_TYPE = 0
        val FOLDER_TYPE = 1
        val IMAGE_TYPE = 2
    }
}