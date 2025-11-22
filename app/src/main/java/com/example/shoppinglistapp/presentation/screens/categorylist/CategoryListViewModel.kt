package com.example.shoppinglistapp.presentation.screens.categorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglistapp.domain.repository.CategoryRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.shoppinglistapp.domain.usecase.AddShoppingItemUseCase
import com.example.shoppinglistapp.domain.usecase.DeleteShoppingItemUseCase
import com.example.shoppinglistapp.domain.usecase.ToggleShoppingItemUseCase
import com.example.shoppinglistapp.domain.usecase.UpdateShoppingItemUseCase

class CategoryListViewModel(
    private val repository: CategoryRepository,
    private val addShoppingItemUseCase: AddShoppingItemUseCase,
    private val deleteShoppingItemUseCase: DeleteShoppingItemUseCase,
    private val updateShoppingItemUseCase: UpdateShoppingItemUseCase,
    private val toggleShoppingItemUseCase: ToggleShoppingItemUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryListState())
    val state: StateFlow<CategoryListState> = _state.asStateFlow()

    private val _effect = Channel<CategoryListEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        loadCategories()
    }

    fun onEvent(event: CategoryListEvent) {
        when (event) {
            is CategoryListEvent.LoadCategories -> loadCategories()
            is CategoryListEvent.AddCategory -> {
                viewModelScope.launch {
                    repository.insertCategory(event.category)
                    _state.update { it.copy(isAddDialogVisible = false) }
                }
            }
            is CategoryListEvent.DeleteCategory -> {
                viewModelScope.launch {
                    repository.deleteCategory(event.category)
                }
            }
            is CategoryListEvent.UpdateCategory -> {
                viewModelScope.launch {
                    repository.updateCategory(event.category)
                    _state.update { it.copy(isEditDialogVisible = false, currentCategory = null) }
                }
            }
            is CategoryListEvent.ShowAddDialog -> {
                _state.update { it.copy(isAddDialogVisible = true) }
            }
            is CategoryListEvent.HideAddDialog -> {
                _state.update { it.copy(isAddDialogVisible = false) }
            }
            is CategoryListEvent.ShowEditDialog -> {
                _state.update { it.copy(isEditDialogVisible = true, currentCategory = event.category) }
            }
            is CategoryListEvent.HideEditDialog -> {
                _state.update { it.copy(isEditDialogVisible = false, currentCategory = null) }
            }
            is CategoryListEvent.OnCategoryClick -> {
                // No longer navigating, maybe expand/collapse? For now do nothing or expand
            }
            is CategoryListEvent.AddItem -> {
                viewModelScope.launch {
                    addShoppingItemUseCase(event.item)
                    _state.update { it.copy(isAddItemDialogVisible = false) }
                }
            }
            is CategoryListEvent.DeleteItem -> {
                viewModelScope.launch {
                    deleteShoppingItemUseCase(event.item)
                }
            }
            is CategoryListEvent.UpdateItem -> {
                viewModelScope.launch {
                    updateShoppingItemUseCase(event.item)
                    _state.update { it.copy(isEditItemDialogVisible = false, itemToEdit = null) }
                }
            }
            is CategoryListEvent.ToggleItem -> {
                viewModelScope.launch {
                    toggleShoppingItemUseCase(event.item)
                }
            }
            is CategoryListEvent.ShowAddItemDialog -> {
                _state.update { it.copy(isAddItemDialogVisible = true, currentCategoryIdForAddItem = event.categoryId) }
            }
            is CategoryListEvent.HideAddItemDialog -> {
                _state.update { it.copy(isAddItemDialogVisible = false, currentCategoryIdForAddItem = null) }
            }
            is CategoryListEvent.ShowEditItemDialog -> {
                _state.update { it.copy(isEditItemDialogVisible = true, itemToEdit = event.item) }
            }
            is CategoryListEvent.HideEditItemDialog -> {
                _state.update { it.copy(isEditItemDialogVisible = false, itemToEdit = null) }
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getCategoriesWithItems().collect { categories ->
                _state.update { it.copy(categories = categories, isLoading = false) }
            }
        }
    }
}

sealed class CategoryListEffect {
    data class NavigateToShoppingList(val categoryId: Int) : CategoryListEffect()
}
