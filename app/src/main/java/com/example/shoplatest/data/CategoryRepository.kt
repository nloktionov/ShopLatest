package com.example.shoplatest.data

import androidx.lifecycle.LiveData

class CategoryRepository(private val dao: CategoryDao) {

    val categories = dao.gelAllCategories()
    val categoriesCount = dao.getCategoriesCount()

    suspend fun insert(category: CategoryEntity) {
        return dao.insert(category)
    }

    suspend fun getCategoryName(categoryName: String):CategoryEntity? {
        return dao.getCategoryName(categoryName)
    }

    suspend fun getCategoriesCount(): LiveData<Int> {
        return dao.getCategoriesCount()
    }
}