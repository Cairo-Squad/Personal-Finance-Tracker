package model

data class Category(
    var categoryId: Int,
    val categoryName: String
)

fun List<Category>.convertToString(): String {
    val listAsString = StringBuilder()

    this.forEach { category ->
        listAsString.append("${category.categoryId}. ${category.categoryName}    ")
    }

    return listAsString.toString()
}