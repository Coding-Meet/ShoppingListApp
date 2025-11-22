package com.example.shoppinglistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppinglistapp.data.local.dao.ShoppingDao
import com.example.shoppinglistapp.data.local.entity.ShoppingItemEntity

import com.example.shoppinglistapp.data.local.dao.CategoryDao
import com.example.shoppinglistapp.data.local.entity.CategoryEntity

@Database(
    entities = [ShoppingItemEntity::class, CategoryEntity::class],
    version = 3
)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract val shoppingDao: ShoppingDao
    abstract val categoryDao: CategoryDao
}
