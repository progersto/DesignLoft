package com.designloft.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.designloft.database.daos.ModelDao
import com.designloft.database.entities.CatalogItem

@Database(
    entities = [
        CatalogItem::class
    ], version = 1, exportSchema = false
)
abstract class DesignLoftDataBase : RoomDatabase() {
    abstract fun getModelDao(): ModelDao
}