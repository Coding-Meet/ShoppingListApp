package com.example.shoppinglistapp.domain.repository

import com.example.shoppinglistapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

import com.example.shoppinglistapp.domain.model.CategoryWithItems

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    fun getCategoriesWithItems(): Flow<List<CategoryWithItems>>
    suspend fun getCategory(id: Int): Category?
    suspend fun insertCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    suspend fun updateCategory(category: Category)
}
