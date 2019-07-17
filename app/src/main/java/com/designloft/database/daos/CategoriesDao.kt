package com.designloft.database.daos

import androidx.room.*
import com.designloft.database.entities.CategoryItem

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg categoryItem: CategoryItem)

    @Query("select * from CategoryItem")
    fun getItemList(): MutableList<CategoryItem>

//    @Query("select * from CategoryItem where type = 1 and parentFolderId is :parentFolderId")
//    fun getFolderList(parentFolderId: Int?): List<CategoryItem>

    @Update
    fun updateModel(categoryItem: CategoryItem)
//    @Query("UPDATE CategoryItem set name =:id where id =:id")
//    fun updateModel(id: Int)

//    @Query("DELETE from CategoryItem where id =:id or parentFolderId =:id")
//    fun delete(id: Int)

}//FileDataDao