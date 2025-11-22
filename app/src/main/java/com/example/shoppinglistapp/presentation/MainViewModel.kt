package com.example.shoppinglistapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglistapp.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> = userPreferencesRepository.isDarkTheme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.toggleTheme(isDark)
        }
    }
}
