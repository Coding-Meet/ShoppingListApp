package com.example.shoppinglistapp.data.mapper

import com.example.shoppinglistapp.data.local.entity.ShoppingItemEntity
import com.example.shoppinglistapp.domain.model.ShoppingItem

fun ShoppingItemEntity.toDomain(): ShoppingItem {
    return ShoppingItem(
        id = id,
        name = name,
        quantity = quantity,
        isBought = isBought,
        categoryId = categoryId
    )
}

fun ShoppingItem.toEntity(): ShoppingItemEntity {
    return ShoppingItemEntity(
        id = id,
        name = name,
        quantity = quantity,
        isBought = isBought,
        categoryId = categoryId
    )
}
