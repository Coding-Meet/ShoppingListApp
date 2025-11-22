package com.example.shoppinglistapp.app.di

import androidx.room.Room
import com.example.shoppinglistapp.data.local.ShoppingDatabase
import com.example.shoppinglistapp.data.repository.CategoryRepositoryImpl
import com.example.shoppinglistapp.data.repository.ShoppingRepositoryImpl
import com.example.shoppinglistapp.data.repository.UserPreferencesRepository
import com.example.shoppinglistapp.domain.repository.CategoryRepository
import com.example.shoppinglistapp.domain.repository.ShoppingRepository
import com.example.shoppinglistapp.domain.usecase.*
import com.example.shoppinglistapp.presentation.MainViewModel
import com.example.shoppinglistapp.presentation.screens.categorylist.CategoryListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ShoppingDatabase::class.java,
            "shopping_db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    single { get<ShoppingDatabase>().shoppingDao }
    single { get<ShoppingDatabase>().categoryDao }

    single<ShoppingRepository> { ShoppingRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    factory { GetShoppingItemsUseCase(get()) }
    factory { AddShoppingItemUseCase(get()) }
    factory { DeleteShoppingItemUseCase(get()) }
    factory { UpdateShoppingItemUseCase(get()) }
    factory { ToggleShoppingItemUseCase(get()) }



    single { UserPreferencesRepository(androidContext()) }


    viewModel { MainViewModel(get()) }
    viewModel { CategoryListViewModel(get(), get(), get(), get(), get()) }
}
