package com.designloft.database.daos

import androidx.room.*
import com.designloft.database.entities.CategoryEntity

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg categoryEntity: CategoryEntity)

    @Query("select * from CategoryEntity")
    fun getItemList(): MutableList<CategoryEntity>

//    @Query("select * from CategoryEntity where type = 1 and parentFolderId is :parentFolderId")
//    fun getFolderList(parentFolderId: Int?): List<CategoryEntity>

    @Update
    fun updateModel(categoryEntity: CategoryEntity)
//    @Query("UPDATE CategoryEntity set name =:id where id =:id")
//    fun updateModel(id: Int)

//    @Query("DELETE from CategoryEntity where id =:id or parentFolderId =:id")
//    fun delete(id: Int)

}//FileDataDao