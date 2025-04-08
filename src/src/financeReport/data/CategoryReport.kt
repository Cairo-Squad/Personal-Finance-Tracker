package src.financeReport.data

data class CategorySummaryReport(
    val type: CategoryType,
    val category: HashMap<String, Float>,
    val total: Float,
) {

    override fun toString(): String {
        val builder = StringBuilder()

        builder.append("$type by Category:\n")
        category.forEach { (name, value) ->
            builder.append("- ${name.padEnd(15)}: %, .2f.\n".format(value))
        }
        builder.append("----------------------------\n")
        builder.append("Total ${type.toString().padEnd(10)}: %, .2f".format(total))

        return builder.toString()
    }

}

enum class CategoryType {
    Income,
    Expenses,
}
