package src.financeReport.data

data class MonthReport(
    val date: String,
    val income: Float,
    val expenses: Float,
    val netBalance: Float = income - expenses,
) {
    override fun toString(): String {
        return """
    📅 Month Report: $date

    🔹 Income       : $income EGP
    🔸 Expenses     : $expenses EGP
    💰 Net Balance  : $netBalance EGP
""".trimIndent()

    }
}
