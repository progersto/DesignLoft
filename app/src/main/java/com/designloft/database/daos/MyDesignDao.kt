package com.designloft.database.daos

import androidx.room.*
import com.designloft.database.entities.MyDesignItem

@Dao
interface MyDesignDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg myDesignItem: MyDesignItem)

    @Query("select * from MyDesignItem")
    fun getItemList(): MutableList<MyDesignItem>

//    @Query("select * from MyDesignItem where type = 1 and parentFolderId is :parentFolderId")
//    fun getFolderList(parentFolderId: Int?): List<MyDesignItem>

    @Update
    fun updateModel(myDesignItem: MyDesignItem)
//    @Query("UPDATE MyDesignItem set name =:id where id =:id")
//    fun updateModel(id: Int)

//    @Query("DELETE from MyDesignItem where id =:id or parentFolderId =:id")
//    fun delete(id: Int
}