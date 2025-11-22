package com.example.shoppinglistapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "shopping_items",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId")]
)
data class ShoppingItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val isBought: Boolean,
    val categoryId: Int
)
