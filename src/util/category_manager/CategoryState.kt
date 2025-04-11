package util.category_manager

import model.Category

sealed class CategoryState {
    object Empty : CategoryState()
    data class FullMatchExists(val existCategory: Category) : CategoryState()
    data class PartialMatchExists(val existCategory:Category) : CategoryState()
    object NewCategory : CategoryState()
}


