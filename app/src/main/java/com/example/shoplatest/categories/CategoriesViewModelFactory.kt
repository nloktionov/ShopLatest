package com.example.shoplatest.categories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoplatest.data.CategoryRepository
import java.lang.IllegalArgumentException

class CategoriesViewModelFactory(
    private val repository: CategoryRepository,
    private val application: Application
): ViewModelProvider.Factory {
    @Suppress
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
            return CategoriesViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}