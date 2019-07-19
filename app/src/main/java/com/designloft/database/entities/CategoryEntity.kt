package com.designloft.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.designloft.models.Category

@Entity(tableName = "CategoryEntity")
class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "name")
    var name: String = ""
) {
    companion object {
        fun toCategory(entity: CategoryEntity, photoList: MutableList<PhotoProductEntity>) =
            Category(
                id = entity.id!!,
                name = entity.name,
                imageList = photoList
            )
    }
}