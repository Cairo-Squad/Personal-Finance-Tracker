package utility

import model.Category

object CategoryUtils {
    fun checkID(categories: MutableList<Category>, userInput: String): Boolean {
        val id = userInput.toIntOrNull() ?: return false

        return categories.any { it.categoryId == id }
    }
}
