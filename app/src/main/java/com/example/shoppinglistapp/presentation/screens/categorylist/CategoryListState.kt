package com.example.shoppinglistapp.presentation.screens.categorylist

import com.example.shoppinglistapp.domain.model.Category
import com.example.shoppinglistapp.domain.model.CategoryWithItems
import com.example.shoppinglistapp.domain.model.ShoppingItem

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<CategoryWithItems> = emptyList(),
    val error: String? = null,
    val isAddDialogVisible: Boolean = false,
    val isEditDialogVisible: Boolean = false,
    val currentCategory: Category? = null,
    val isAddItemDialogVisible: Boolean = false,
    val currentCategoryIdForAddItem: Int? = null,
    val isEditItemDialogVisible: Boolean = false,
    val itemToEdit: ShoppingItem? = null
)
