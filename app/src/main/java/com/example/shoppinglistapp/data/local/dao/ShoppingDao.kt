package com.example.shoppinglistapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglistapp.data.local.entity.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {
    @Query("SELECT * FROM shopping_items WHERE categoryId = :categoryId")
    fun getShoppingItems(categoryId: Int): Flow<List<ShoppingItemEntity>>

    @Query("SELECT * FROM shopping_items WHERE id = :id")
    suspend fun getShoppingItem(id: Int): ShoppingItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(item: ShoppingItemEntity)

    @Delete
    suspend fun deleteShoppingItem(item: ShoppingItemEntity)

    @Update
    suspend fun updateShoppingItem(item: ShoppingItemEntity)
}
