package util

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
    ): Int {

     if (newCategoryName.isEmpty()) {
         return 0 // Invalid
     }

     val userNG = newCategoryName.lowercase()
     for (category in categories) {
         val categoryName= category.categoryName.lowercase()
         // already exist
         if (categoryName==userNG){
             return 1
         }
         //  categoryName is match  in list of category
         if (categoryName.contains(userNG)) {
             return 2
         }

     }
     // no found . new category added
     val newId = categories.size + 1
     val newCategory = Category(newId, newCategoryName)
     categories.add(newCategory)
     return 3

    }

    fun addCategoryDecision(
        categories: MutableList<Category>,
        newCategoryName: String,
        userAddOrNot: Int
    ):Boolean {
        when (userAddOrNot) {
            1 -> {
               //You have chosen to use an existing category.
                return true
            }
            2 -> {
                //  add new category
                if (newCategoryName.isNotEmpty()) {
                    val newId = categories.size + 1
                    categories.add(Category(newId, newCategoryName))
                }
                return true
            }
            else -> {
                //println("Invalid choice. Please enter 1 or 2.")
                return false
            }
        }
    }

}