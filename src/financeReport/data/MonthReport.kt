package financeReport.data

import java.time.LocalDateTime

data class MonthReport(
    val date: LocalDateTime,
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
