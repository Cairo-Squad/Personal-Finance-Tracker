package financeReport.data

data class CategoryReport(
    val transactionType: TransactionType,
    val categories: Map<String, Double>,
    val total: Double,
) {

    override fun toString(): String {
        val builder = StringBuilder()

        builder.append("$transactionType by Category:\n")
        categories.forEach { (name, value) ->
            builder.append("- ${name.padEnd(15)}: %, .2f.\n".format(value))
        }
        builder.append("----------------------------\n")
        builder.append("Total ${transactionType.toString().padEnd(10)}: %, .2f".format(total))

        return builder.toString()
    }

}

