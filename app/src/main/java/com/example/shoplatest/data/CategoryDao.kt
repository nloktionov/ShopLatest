package com.example.shoplatest.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(category: CategoryEntity)

    @Query("SELECT * FROM category_table ORDER BY categoryName ASC")
    fun gelAllCategories(): LiveData<List<CategoryEntity>>

    @Query("SELECT * FROM category_table WHERE categoryName like :categoryName")
    fun getCategoryName(categoryName: String): CategoryEntity?

    @Query("SELECT COUNT(categoryName) FROM category_table")
    fun getCategoriesCount(): LiveData<Int>

    @Query("DELETE FROM category_table")
    fun deleteAll()
}