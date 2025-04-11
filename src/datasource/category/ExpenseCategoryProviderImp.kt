package datasource.category
import model.Category

class ExpenseCategoryProviderImp : CategoryProvider {
    private val _categories = mutableListOf(
        Category(1, "Groceries"),
        Category(2, "Rent"),
        Category(3, "Utilities"),
        Category(4, "Entertainment"),
        Category(5, "Transportation"),
        Category(6, "Food"),
        Category(7, "Other")
    )

    override fun getCategories(): List<Category> = _categories
    override fun addCategory(category: Category) {
        _categories.add(category)
    }
}