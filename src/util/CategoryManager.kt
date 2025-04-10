package util

import model.Category

object CategoryManager {
    fun checkCategoryManager(choose:Int,categories: MutableList<Category>){
        if(choose==1)
        {
            println("enter number of category ")
            val userInput= readln()
            checkCategorySelection(categories,userInput)
        }else{
            println("enter name of category ")
            val userInput= readln()
            checkNewCategoryName(categories,userInput)
        }

    }


    fun checkCategorySelection(
        categories: MutableList<Category>,
        userInput: String
    ): Boolean {
        if (userInput == "") {
            return false
        }

        val id: Int?
        val userInt = userInput.toIntOrNull()
        if (userInt != null) {
            id = userInt
        } else {
            return false
        }
        if (0 < id || id < categories.size) {

            for (category in categories) {
                if (category.categoryId == id) {
                    return true
                }
            }
        }

        return false
    }

    fun checkNewCategoryName(
        categories: MutableList<Category>,
        userInput: String
    ): Boolean {
        if (userInput.isEmpty()) {
            return false
        }

        var Found = false
        val inputName = userInput.lowercase()

        for (category in categories) {
            val categoryName = category.categoryName.lowercase()

            if (categoryName.contains(inputName)) {
                Found = true
                println("Found `$inputName` matches `$categoryName`")
                println("Enter 1 to if you want $categoryName category, or 2 to create a new category$inputName:")

                val userDecision = readlnOrNull()?.toIntOrNull()

                if (userDecision == 1) {
                    println("this category already exist")
                } else if (userDecision == 2) {
                    val newId = categories.size + 1
                    val newCategory = Category(newId, userInput)
                    categories.add(newCategory) // new
                    // println(categories)
                } else {
                    return false
                }
                break
            }
        }

        if (!Found) {
            val newId = categories.size + 1
            val newCategory = Category(newId, userInput)
            categories.add(newCategory)
            // println(categories)
        }

        return true
    }

}