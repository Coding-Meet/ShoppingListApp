package com.example.shoppinglistapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object CategoryList : Screen()
}
