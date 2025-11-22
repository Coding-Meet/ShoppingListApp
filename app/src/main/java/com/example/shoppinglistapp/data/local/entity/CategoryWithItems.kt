package com.example.shoppinglistapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithItems(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val items: List<ShoppingItemEntity>
)
