package src.financeReport

import src.financeReport.data.MonthReport
class FinanceReporter : IMonthlyFinanceReporter, IMonthlyCategoryReporter {

    /***
     * This function calculate the total income, total expenses, and net balance for specific month of a year.
     *
     *  * Example:
     * ```
     *  * val report = financeReporter.getMonthReport(2024, 4)
     *  * println("Income: ${report.totalIncome}, Expenses: ${report.totalExpenses}, Net Balance: $report.netBalance")
     * ```
     *
     * @param year The year for which to generate the report. Must be between 2020 and 2025.
     * @param month The month (1-12) for which to generate the report.
     *
     * @return A [MonthReport] which contains income, expenses, and netBalance data for the month.
     *
     * @throws IllegalArgumentException If the year or month is out of the valid range.
     * @throws NoSuchElementException If no financial data exists for the given month and year.
     */
    override fun getMonthReport(year: Int, month: Int): MonthReport {
        return MonthReport(
            date = "$year-$month",
            income = getMonthTotalIncome(year , month),
            expenses = getMonthTotalExpenses(year , month),
        )
    }

    override fun getMonthTotalIncome(year: Int, month: Int): Float {
        if (year !in 2020..2025) throw IllegalArgumentException("Invalid year, enter year between 2020 and 2025")
        if (month !in 1..12) throw IllegalArgumentException("Invalid month")
        return 1f
    }

    override fun getMonthTotalExpenses(year: Int, month: Int): Float {
        TODO("Not yet implemented")
    }

    override fun getMonthNetBalance(year: Int, month: Int): Float {
        TODO("Not yet implemented")
    }


    override fun getMonthIncomeByCategory(category: String, year: Int, month: Int): Float {
        TODO("Not yet implemented")
    }

    override fun getMonthExpensesByCategory(category: String, year: Int, month: Int): Float {
        TODO("Not yet implemented")
    }

}
