package com.example.shoppinglistapp.presentation.screens.categorylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Light
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoppinglistapp.presentation.theme.ShoppingListAppTheme
import com.example.shoppinglistapp.domain.model.Category
import org.koin.androidx.compose.koinViewModel

import com.example.shoppinglistapp.domain.model.CategoryWithItems
import com.example.shoppinglistapp.presentation.components.AddEditItemDialog
import com.example.shoppinglistapp.presentation.components.ShoppingItemRow
import com.example.shoppinglistapp.domain.model.ShoppingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel = koinViewModel(),
    onThemeToggle: (Boolean) -> Unit,
    isDarkTheme: Boolean
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping List") },
                actions = {
                    IconButton(onClick = { onThemeToggle(!isDarkTheme) }) {
                        Icon(imageVector =
                            if (isDarkTheme) Icons.Default.Light else Icons.Default.DarkMode
                            , contentDescription = "Toggle Theme")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(CategoryListEvent.ShowAddDialog) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Category")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(state.categories) { categoryWithItems ->
                        CategorySection(
                            categoryWithItems = categoryWithItems,
                            onEditCategory = { viewModel.onEvent(CategoryListEvent.ShowEditDialog(categoryWithItems.category)) },
                            onDeleteCategory = { viewModel.onEvent(CategoryListEvent.DeleteCategory(categoryWithItems.category)) },
                            onAddItem = { viewModel.onEvent(CategoryListEvent.ShowAddItemDialog(categoryWithItems.category.id)) },
                            onToggleItem = { viewModel.onEvent(CategoryListEvent.ToggleItem(it)) },
                            onEditItem = { viewModel.onEvent(CategoryListEvent.ShowEditItemDialog(it)) },
                            onDeleteItem = { viewModel.onEvent(CategoryListEvent.DeleteItem(it)) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                if (state.categories.isEmpty() && !state.isLoading) {
                    Text(
                        text = "No categories. Add one!",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        if (state.isAddDialogVisible) {
            AddEditCategoryDialog(
                onDismiss = { viewModel.onEvent(CategoryListEvent.HideAddDialog) },
                onConfirm = { name ->
                    viewModel.onEvent(CategoryListEvent.AddCategory(Category(name = name)))
                }
            )
        }

        if (state.isEditDialogVisible) {
            AddEditCategoryDialog(
                category = state.currentCategory,
                onDismiss = { viewModel.onEvent(CategoryListEvent.HideEditDialog) },
                onConfirm = { name ->
                    state.currentCategory?.let { category ->
                        viewModel.onEvent(CategoryListEvent.UpdateCategory(category.copy(name = name)))
                    }
                }
            )
        }

        if (state.isAddItemDialogVisible) {
            AddEditItemDialog(
                onDismiss = { viewModel.onEvent(CategoryListEvent.HideAddItemDialog) },
                onConfirm = { name, quantity ->
                    state.currentCategoryIdForAddItem?.let { categoryId ->
                        viewModel.onEvent(CategoryListEvent.AddItem(ShoppingItem(name = name, quantity = quantity, categoryId = categoryId)))
                    }
                }
            )
        }

        if (state.isEditItemDialogVisible) {
            AddEditItemDialog(
                item = state.itemToEdit,
                onDismiss = { viewModel.onEvent(CategoryListEvent.HideEditItemDialog) },
                onConfirm = { name, quantity ->
                    state.itemToEdit?.let { item ->
                        viewModel.onEvent(CategoryListEvent.UpdateItem(item.copy(name = name, quantity = quantity)))
                    }
                }
            )
        }
    }
}

@Composable
fun CategorySection(
    categoryWithItems: CategoryWithItems,
    onEditCategory: () -> Unit,
    onDeleteCategory: () -> Unit,
    onAddItem: () -> Unit,
    onToggleItem: (ShoppingItem) -> Unit,
    onEditItem: (ShoppingItem) -> Unit,
    onDeleteItem: (ShoppingItem) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = categoryWithItems.category.name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Row {
                IconButton(onClick = onAddItem) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Item")
                }
                IconButton(onClick = onEditCategory) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Category")
                }
                IconButton(onClick = onDeleteCategory) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Category")
                }
            }
        }
        
        if (categoryWithItems.items.isEmpty()) {
            Text(
                text = "No items",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            categoryWithItems.items.forEach { item ->
                ShoppingItemRow(
                    item = item,
                    onToggle = { onToggleItem(item) },
                    onEdit = { onEditItem(item) }, 
                    onDelete = { onDeleteItem(item) }
                )
            }
        }
    }
}

@Composable
fun AddEditCategoryDialog(
    category: Category? = null,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var name by remember { mutableStateOf(category?.name ?: "") }
    var nameError by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = if (category == null) "Add Category" else "Edit Category") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = null
                    },
                    label = { Text("Category Name") },
                    isError = nameError != null,
                    singleLine = true
                )
                if (nameError != null) {
                    Text(
                        text = nameError!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isBlank()) {
                        nameError = "Name cannot be empty"
                    } else {
                        onConfirm(name)
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CategoryListScreenPreview() {
    ShoppingListAppTheme {
        CategoryListScreen(
            onThemeToggle = {},
            isDarkTheme = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategorySectionPreview() {
    ShoppingListAppTheme {
        CategorySection(
            categoryWithItems = CategoryWithItems(
                category = Category(name = "Fruits", id = 1),
                items = listOf(
                    ShoppingItem(name = "Apple", quantity = 5, categoryId = 1),
                    ShoppingItem(name = "Banana", quantity = 2, categoryId = 1)
                )
            ),
            onEditCategory = {},
            onDeleteCategory = {},
            onAddItem = {},
            onToggleItem = {},
            onEditItem = {},
            onDeleteItem = {}
        )
    }
}
