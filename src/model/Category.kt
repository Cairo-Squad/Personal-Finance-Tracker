package model

data class Category(
    var categoryId: Int,
    val categoryName: String
)

fun List<Category>.convertToString(): String {
    val listAsString = StringBuilder()

    this.forEach { category ->
        // TODO: Check the space using \t!!
        listAsString.append("${category.categoryId}. ${category.categoryName}\t")
    }

    return listAsString.toString()
}