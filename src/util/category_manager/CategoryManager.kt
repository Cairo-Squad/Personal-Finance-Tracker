package util.category_manager

import model.Category

object CategoryManager {

    fun validateCategorySelection(
        categories:List<Category>,
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
        categories: List<Category>,
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

         return CategoryState.PartialMatchExists(maxCategoryMatching )
     }

     return CategoryState.NewCategory
    }


    private fun findLargestMatchOfNameCategory(newNameCategory: String, categories: List<Category>): Category? {
        return categories.maxByOrNull { category ->
            category.categoryName.zip(newNameCategory).count { it.first == it.second }>1
        }
    }

}