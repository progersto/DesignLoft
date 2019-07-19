package com.designloft.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.designloft.database.daos.CategoriesDao
import com.designloft.database.daos.MyDesignDao
import com.designloft.database.daos.PhotoProductDao
import com.designloft.database.daos.ProductsDao
import com.designloft.database.entities.CategoryEntity
import com.designloft.database.entities.MyDesignEntity
import com.designloft.database.entities.PhotoProductEntity
import com.designloft.database.entities.ProductEntity

@Database(
    entities = [
        CategoryEntity::class,
        ProductEntity::class,
        MyDesignEntity::class,
        PhotoProductEntity::class
    ], version = 1, exportSchema = false
)
abstract class DesignLoftDataBase : RoomDatabase() {
    abstract fun getCategoriesDao(): CategoriesDao
    abstract fun getProductsDao(): ProductsDao
    abstract fun getMyDesignDao(): MyDesignDao
    abstract fun getPhotoProductDao(): PhotoProductDao
}