package com.designloft.database.daos

import androidx.room.*
import com.designloft.database.entities.MyDesignEntity

@Dao
interface MyDesignDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg myDesignEntity: MyDesignEntity)

    @Query("select * from MyDesignEntity")
    fun getItemList(): MutableList<MyDesignEntity>

//    @Query("select * from MyDesignEntity where type = 1 and parentFolderId is :parentFolderId")
//    fun getFolderList(parentFolderId: Int?): List<MyDesignEntity>

    @Update
    fun updateModel(myDesignEntity: MyDesignEntity)
//    @Query("UPDATE MyDesignEntity set name =:id where id =:id")
//    fun updateModel(id: Int)

//    @Query("DELETE from MyDesignEntity where id =:id or parentFolderId =:id")
//    fun delete(id: Int
}