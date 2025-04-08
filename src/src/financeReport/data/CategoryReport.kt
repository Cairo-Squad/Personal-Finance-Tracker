package src.financeReport.data

data class CategorySummaryReport(
    val financeType: FinanceType,
    val categories: HashMap<String, Float>,
    val total: Float,
) {

    override fun toString(): String {
        val builder = StringBuilder()

        builder.append("$financeType by Category:\n")
        categories.forEach { (name, value) ->
            builder.append("- ${name.padEnd(15)}: %, .2f.\n".format(value))
        }
        builder.append("----------------------------\n")
        builder.append("Total ${financeType.toString().padEnd(10)}: %, .2f".format(total))

        return builder.toString()
    }

}

enum class FinanceType {
    Income,
    Expenses,
}
