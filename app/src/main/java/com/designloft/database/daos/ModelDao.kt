package com.designloft.database.daos

import androidx.room.*
import com.designloft.database.entities.Model

@Dao
interface ModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg model: Model)

    @Query("select * from Model where type = 2 and parentFolderId is :parentFolderId")
    fun getImageList(parentFolderId: Int?): List<Model>

    @Query("select * from Model where type = 1 and parentFolderId is :parentFolderId")
    fun getFolderList(parentFolderId: Int?): List<Model>

    @Update
    fun updateModel(model: Model)
//    @Query("UPDATE Model set name =:id where id =:id")
//    fun updateModel(id: Int)

    @Query("DELETE from Model where id =:id or parentFolderId =:id")
    fun delete(id: Int)

}//FileDataDao