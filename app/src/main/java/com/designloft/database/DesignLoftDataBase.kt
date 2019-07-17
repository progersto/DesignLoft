package com.designloft.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.designloft.database.daos.CategoriesDao
import com.designloft.database.daos.MyDesignDao
import com.designloft.database.daos.ProductsDao
import com.designloft.database.entities.CategoryItem
import com.designloft.database.entities.MyDesignItem
import com.designloft.database.entities.ProductItem

@Database(
    entities = [
        CategoryItem::class,
        ProductItem::class,
        MyDesignItem::class
    ], version = 1, exportSchema = false
)
abstract class DesignLoftDataBase : RoomDatabase() {
    abstract fun getCategoriesDao(): CategoriesDao
    abstract fun getProductsDao(): ProductsDao
    abstract fun getMyDesignDao(): MyDesignDao
}