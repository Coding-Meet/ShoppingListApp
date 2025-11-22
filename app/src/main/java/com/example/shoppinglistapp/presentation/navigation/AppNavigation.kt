package com.example.shoppinglistapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglistapp.presentation.MainViewModel
import com.example.shoppinglistapp.presentation.screens.categorylist.CategoryListScreen
import org.koin.androidx.compose.koinViewModel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.shoppinglistapp.presentation.screens.categorylist.CategoryListScreen
import androidx.navigation.toRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.CategoryList,
        modifier = modifier
    ) {
        composable<Screen.CategoryList> {
            CategoryListScreen(
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle
            )
        }
    }
}
