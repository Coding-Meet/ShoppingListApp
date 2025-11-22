package com.example.shoppinglistapp.data.repository

import com.example.shoppinglistapp.data.local.dao.ShoppingDao
import com.example.shoppinglistapp.data.mapper.toDomain
import com.example.shoppinglistapp.data.mapper.toEntity
import com.example.shoppinglistapp.domain.model.ShoppingItem
import com.example.shoppinglistapp.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingRepositoryImpl(
    private val dao: ShoppingDao
) : ShoppingRepository {

    override fun getShoppingItems(categoryId: Int): Flow<List<ShoppingItem>> {
        return dao.getShoppingItems(categoryId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getShoppingItem(id: Int): ShoppingItem? {
        return dao.getShoppingItem(id)?.toDomain()
    }

    override suspend fun insertShoppingItem(item: ShoppingItem) {
        dao.insertShoppingItem(item.toEntity())
    }

    override suspend fun deleteShoppingItem(item: ShoppingItem) {
        dao.deleteShoppingItem(item.toEntity())
    }

    override suspend fun updateShoppingItem(item: ShoppingItem) {
        dao.updateShoppingItem(item.toEntity())
    }
}
