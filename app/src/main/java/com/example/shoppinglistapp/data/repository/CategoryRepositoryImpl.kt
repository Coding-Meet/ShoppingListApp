package com.example.shoppinglistapp.data.repository

import com.example.shoppinglistapp.data.local.dao.CategoryDao
import com.example.shoppinglistapp.data.local.entity.CategoryEntity
import com.example.shoppinglistapp.data.mapper.toDomain
import com.example.shoppinglistapp.data.mapper.toEntity
import com.example.shoppinglistapp.domain.model.Category
import com.example.shoppinglistapp.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val dao: CategoryDao
) : CategoryRepository {

    override fun getCategories(): Flow<List<Category>> {
        return dao.getCategories().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getCategoriesWithItems(): Flow<List<com.example.shoppinglistapp.domain.model.CategoryWithItems>> {
        return dao.getCategoriesWithItems().map { entities ->
            entities.map {
                com.example.shoppinglistapp.domain.model.CategoryWithItems(
                    category = it.category.toDomain(),
                    items = it.items.map { item -> item.toDomain() }
                )
            }
        }
    }

    override suspend fun getCategory(id: Int): Category? {
        return dao.getCategory(id)?.toDomain()
    }

    override suspend fun insertCategory(category: Category) {
        dao.insertCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        dao.deleteCategory(category.toEntity())
    }

    override suspend fun updateCategory(category: Category) {
        dao.updateCategory(category.toEntity())
    }

    private fun CategoryEntity.toDomain(): Category {
        return Category(id = id, name = name)
    }

    private fun Category.toEntity(): CategoryEntity {
        return CategoryEntity(id = id, name = name)
    }
}
