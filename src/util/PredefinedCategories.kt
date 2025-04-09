package util

import model.Category

object PredefinedCategories {
    private val _incomeCategories = mutableListOf(
        Category(1, "Salary"),
        Category(2, "Investments"),
        Category(3, "Business"),
        Category(4, "Other")
    )
    val incomeCategories: List<Category> get() = _incomeCategories


    private val _expenseCategories = mutableListOf(
        Category(1, "Groceries"),
        Category(2, "Rent"),
        Category(3, "Utilities"),
        Category(4, "Entertainment"),
        Category(5, "Transportation"),
        Category(6, "Food"),
        Category(7, "Other")
    )
    val expenseCategories: List<Category> get() = _expenseCategories


    fun addIncomeCategory(category: Category) {
        _incomeCategories.add(category)
    }

    fun addExpenseCategory(category: Category) {
        _expenseCategories.add(category)
    }
}