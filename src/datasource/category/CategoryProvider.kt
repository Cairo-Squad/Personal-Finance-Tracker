package datasource.category

import model.Category

interface CategoryProvider {
    fun getCategories(): List<Category>
    fun addCategory(category: Category)
}