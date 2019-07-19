package com.designloft.database.daos

import androidx.room.*
import com.designloft.database.entities.ProductEntity

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg productEntity: ProductEntity)

    @Query("select * from ProductEntity where category_id = :categoryId")
    fun getProductsByCategoryId(categoryId: Int): MutableList<ProductEntity>

    @Query("select * from ProductEntity where favorite = 1")
    fun getFavorites(): MutableList<ProductEntity>
    
    @Query("select * from ProductEntity where id = :productId")
    fun getProductByyId(productId: Int): ProductEntity

//    @Query("select * from ProductEntity where type = 1 and parentFolderId is :parentFolderId")
//    fun getFolderList(parentFolderId: Int?): List<CategoryEntity>

    @Update
    fun updateProduct(vararg productEntity: ProductEntity): Int

//    @Query("UPDATE ProductEntity set name =:id where id =:id")
//    fun updateModel(id: Int)

//    @Query("DELETE from ProductEntity where id =:id or parentFolderId =:id")
//    fun delete(id: Int)
}