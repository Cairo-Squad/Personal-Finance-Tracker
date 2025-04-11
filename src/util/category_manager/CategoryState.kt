package util.category_manager

import model.Category

sealed class CategoryState {
    object Empty : CategoryState()
    object FullMatchExists : CategoryState()
    data class PartialMatchExists(val existCategory: String,val nameNewCategory:String ) : CategoryState()
    data class NewCategory(val addedNewCategory:String) : CategoryState()
}


