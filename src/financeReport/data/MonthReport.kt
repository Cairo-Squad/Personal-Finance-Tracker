package financeReport.data

data class MonthReport(
    val date: String,
    val income: Double,
    val expenses: Double,
    val netBalance: Double = income - expenses,
) {
    override fun toString(): String {
        return """
    📅 Month Report: $date

    🔹 Income       : $income 
    🔸 Expenses     : $expenses 
    💰 Net Balance  : $netBalance 
""".trimIndent()

    }
}
