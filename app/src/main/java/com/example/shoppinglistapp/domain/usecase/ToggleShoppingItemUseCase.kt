package com.example.shoppinglistapp.domain.usecase

import com.example.shoppinglistapp.domain.model.ShoppingItem
import com.example.shoppinglistapp.domain.repository.ShoppingRepository

class ToggleShoppingItemUseCase(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(item: ShoppingItem) {
        repository.updateShoppingItem(item.copy(isBought = !item.isBought))
    }
}
