package com.example.shoppinglistapp.presentation.screens.categorylist

import com.example.shoppinglistapp.domain.model.Category
import com.example.shoppinglistapp.domain.model.ShoppingItem

sealed class CategoryListEvent {
    object LoadCategories : CategoryListEvent()
    data class AddCategory(val category: Category) : CategoryListEvent()
    data class DeleteCategory(val category: Category) : CategoryListEvent()
    data class UpdateCategory(val category: Category) : CategoryListEvent()
    object ShowAddDialog : CategoryListEvent()
    object HideAddDialog : CategoryListEvent()
    data class ShowEditDialog(val category: Category) : CategoryListEvent()
    object HideEditDialog : CategoryListEvent()
    data class OnCategoryClick(val category: Category) : CategoryListEvent()
    
    // Item Events
    data class AddItem(val item: ShoppingItem) : CategoryListEvent()
    data class DeleteItem(val item: ShoppingItem) : CategoryListEvent()
    data class UpdateItem(val item: ShoppingItem) : CategoryListEvent()
    data class ToggleItem(val item: ShoppingItem) : CategoryListEvent()
    data class ShowAddItemDialog(val categoryId: Int) : CategoryListEvent()
    object HideAddItemDialog : CategoryListEvent()
    data class ShowEditItemDialog(val item: ShoppingItem) : CategoryListEvent()
    object HideEditItemDialog : CategoryListEvent()
}
