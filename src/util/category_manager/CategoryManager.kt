package util.category_manager

import model.Category

object CategoryManager {

    fun validateCategorySelection(
        categories: MutableList<Category>,
        selectedCategoryId: String
    ): Boolean {
        if (selectedCategoryId == "") {
            return false
        }

        val id: Int?
        val selectedCategoryIdInt = selectedCategoryId.toIntOrNull()

        if (selectedCategoryIdInt != null) {
            id = selectedCategoryIdInt
        } else {
            return false
        }

        for (category in categories) {
            if (category.categoryId == id) {
                return true
            }
        }
        return false
    }

 fun validateNewCategoryName(
        categories: MutableList<Category>,
        newCategoryName: String
    ): CategoryState {

     if (newCategoryName.isEmpty()) {
         return CategoryState.Empty// Invalid
     }

     val userNG = newCategoryName.lowercase()
     for (category in categories) {
         val categoryName= category.categoryName.lowercase()
         // already exist
         if (categoryName==userNG){
             return CategoryState.FullMatchExists
         }
     }
     // Find the category with the largest match
     val maxCategoryMatching = findLargestMatchOfNameCategory(userNG, categories)
     if (maxCategoryMatching != null) {
         return CategoryState.PartialMatchExists(maxCategoryMatching.categoryName  , userNG)
     }
     // no found . new category added
     val newId = categories.size + 1
     val newCategory = Category(newId, newCategoryName)
     categories.add(newCategory)
     return CategoryState.NewCategory(userNG)
    }

    fun addCategoryDecision(
        categories: MutableList<Category>,
        newCategoryName: String,
        userDecision: UserDecisionOfNewCategory
    ): Boolean {
        when (userDecision) {
            UserDecisionOfNewCategory.EXISTING_CATEGORY -> {
                // You have chosen to use an existing category.
               return true
            }
            UserDecisionOfNewCategory.NEW_CATEGORY -> {
                // Add new category
                if (newCategoryName.isNotEmpty()) {
                    val newId = categories.size + 1
                    categories.add(Category(newId, newCategoryName))
                }
               return true
            }
            else -> {
                // Invalid choice
               return false
            }
        }
    }
    private fun findLargestMatchOfNameCategory(newNameCategory: String, categories: MutableList<Category>): Category? {
        return categories.maxByOrNull { category ->
            category.categoryName.zip(newNameCategory).count { it.first == it.second }>1
        }
    }

}