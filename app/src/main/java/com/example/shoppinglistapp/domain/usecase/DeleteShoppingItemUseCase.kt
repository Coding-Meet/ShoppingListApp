package com.example.shoppinglistapp.domain.usecase

import com.example.shoppinglistapp.domain.model.ShoppingItem
import com.example.shoppinglistapp.domain.repository.ShoppingRepository

class DeleteShoppingItemUseCase(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(item: ShoppingItem) {
        repository.deleteShoppingItem(item)
    }
}
