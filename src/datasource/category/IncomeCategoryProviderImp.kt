package datasource.category

import model.Category

object IncomeCategoryProviderImp : CategoryProvider {
    private val _categories = mutableListOf(
        Category(1, "Salary"),
        Category(2, "Investments"),
        Category(3, "Business"),
        Category(4, "Other")
    )

    override fun getCategories(): List<Category> = _categories
    override fun addCategory(category: Category) {
        _categories.add(category)
    }
}