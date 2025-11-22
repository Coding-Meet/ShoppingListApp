package com.example.shoppinglistapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.shoppinglistapp.data.local.entity.CategoryEntity
import com.example.shoppinglistapp.data.local.entity.CategoryWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Transaction
    @Query("SELECT * FROM categories")
    fun getCategoriesWithItems(): Flow<List<CategoryWithItems>>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategory(id: Int): CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Update
    suspend fun updateCategory(category: CategoryEntity)
}
