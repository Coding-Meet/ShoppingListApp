package com.example.shoppinglistapp.domain.usecase

import com.example.shoppinglistapp.domain.model.ShoppingItem
import com.example.shoppinglistapp.domain.repository.ShoppingRepository

class AddShoppingItemUseCase(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(item: ShoppingItem) {
        if (item.name.isBlank()) {
            throw IllegalArgumentException("Item name cannot be empty")
        }
        repository.insertShoppingItem(item)
    }
}
