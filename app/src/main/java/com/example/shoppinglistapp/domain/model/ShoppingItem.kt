package com.example.shoppinglistapp.domain.model

data class ShoppingItem(
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val isBought: Boolean = false,
    val categoryId: Int
)
