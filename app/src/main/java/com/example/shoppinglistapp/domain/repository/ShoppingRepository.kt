package com.example.shoppinglistapp.domain.repository

import com.example.shoppinglistapp.domain.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {
    fun getShoppingItems(categoryId: Int): Flow<List<ShoppingItem>>
    suspend fun getShoppingItem(id: Int): ShoppingItem?
    suspend fun insertShoppingItem(item: ShoppingItem)
    suspend fun deleteShoppingItem(item: ShoppingItem)
    suspend fun updateShoppingItem(item: ShoppingItem)
}
