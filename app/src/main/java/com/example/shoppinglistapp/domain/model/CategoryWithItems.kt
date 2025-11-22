package com.example.shoppinglistapp.domain.model

data class CategoryWithItems(
    val category: Category,
    val items: List<ShoppingItem>
)
