package com.designloft.database.daos

import androidx.room.*
import com.designloft.database.entities.ProductItem

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg productItem: ProductItem)

    @Query("select * from ProductItem")
    fun getItemList(): MutableList<ProductItem>

//    @Query("select * from ProductItem where type = 1 and parentFolderId is :parentFolderId")
//    fun getFolderList(parentFolderId: Int?): List<CategoryItem>

    @Update
    fun updateModel(productItem: ProductItem)
//    @Query("UPDATE ProductItem set name =:id where id =:id")
//    fun updateModel(id: Int)

//    @Query("DELETE from ProductItem where id =:id or parentFolderId =:id")
//    fun delete(id: Int)
}