package com.designloft.database.daos

import androidx.room.*
import com.designloft.database.entities.CatalogItem

@Dao
interface ModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg catalogItem: CatalogItem)

    @Query("select * from CatalogItem")
    fun getItemList(): MutableList<CatalogItem>

//    @Query("select * from CatalogItem where type = 1 and parentFolderId is :parentFolderId")
//    fun getFolderList(parentFolderId: Int?): List<CatalogItem>

    @Update
    fun updateModel(catalogItem: CatalogItem)
//    @Query("UPDATE CatalogItem set name =:id where id =:id")
//    fun updateModel(id: Int)

//    @Query("DELETE from CatalogItem where id =:id or parentFolderId =:id")
//    fun delete(id: Int)

}//FileDataDao