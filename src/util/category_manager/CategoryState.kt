package util.category_manager

import model.Category

sealed class CategoryState {
    object Empty : CategoryState()
    object FullMatchExists : CategoryState()
    data class PartialMatchExists(val existCategory:Category) : CategoryState()
    object NewCategory : CategoryState()
}


