package com.designloft.database.daos

import androidx.room.*
import com.designloft.database.entities.PhotoProductEntity

@Dao
interface PhotoProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photoProductEntity: PhotoProductEntity)

    @Query("select * from PhotoProductEntity where product_id = :productId")
    fun getPhotoProductListByProductId(productId: Int): MutableList<PhotoProductEntity>

    @Query("select * from PhotoProductEntity where category_id = :categoryId")
    fun getPhotoProductByCategoryId(categoryId: Int): MutableList<PhotoProductEntity>

//    @Query("select * from PhotoProductEntity where type = 1 and parentFolderId is :parentFolderId")
//    fun getFolderList(parentFolderId: Int?): List<CategoryEntity>

    @Update
    fun updatePhoto(productEntityItem: PhotoProductEntity)
//    @Query("UPDATE PhotoProductEntity set name =:id where id =:id")
//    fun updateModel(id: Int)

//    @Query("DELETE from PhotoProductEntity where id =:id or parentFolderId =:id")
//    fun delete(id: Int)
}
