package com.example.shoppinglistapp.domain.usecase

import com.example.shoppinglistapp.domain.model.ShoppingItem
import com.example.shoppinglistapp.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow

class GetShoppingItemsUseCase(
    private val repository: ShoppingRepository
) {
    operator fun invoke(categoryId: Int): Flow<List<ShoppingItem>> {
        return repository.getShoppingItems(categoryId)
    }
}
