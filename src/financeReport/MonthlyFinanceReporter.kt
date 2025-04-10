package financeReport

import financeReport.data.*
import java.time.Instant
import java.time.ZoneId

class MonthlyFinanceReporter(private val transactions: List<Transaction>) : IMonthlyFinanceReporter {

    /***
     * This function calculate the total income, total expenses, and net balance for specific month of a year.
     *
     *  * Example:
     * ```
     *  * val report = monthlyFinanceReporter.getMonthReport(2024, 4)
     *    println(report.toString)
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
            income = getMonthTotalIncome(year, month) ?: 0.0,
            expenses = getMonthTotalExpenses(year, month) ?: 0.0,
        )
    }

    override fun getMonthTotalIncome(year: Int, month: Int): Double? {
        validateDate(year, month)

        val matchedTransactions = transactions.filter {
            it.transactionType == TransactionType.INCOME &&
                    it.transactionDate.toLocalYear() == year &&
                    it.transactionDate.toLocalMonth() == month
        }

        return if (matchedTransactions.isEmpty()) null
        else matchedTransactions.sumOf { it.transactionAmount }
    }

    override fun getMonthTotalExpenses(year: Int, month: Int): Double? {
        validateDate(year, month)

        val matchedTransactions = transactions.filter {
            it.transactionType == TransactionType.EXPENSE &&
                    it.transactionDate.toLocalYear() == year &&
                    it.transactionDate.toLocalMonth() == month
        }

        return if (matchedTransactions.isEmpty()) null
        else matchedTransactions.sumOf { it.transactionAmount }
    }

    override fun getMonthNetBalance(year: Int, month: Int): Double? {
        validateDate(year, month)

        val matchedTransactions: List<Transaction> = transactions.filter {
            it.transactionDate.toLocalYear() == year &&
                    it.transactionDate.toLocalMonth() == month
        }

        return if (matchedTransactions.isEmpty()) null
        else return matchedTransactions.sumOf {
            if (it.transactionType == TransactionType.INCOME) it.transactionAmount else it.transactionAmount * -1
        }
    }


    override fun getMonthReportOfAllCategories(transactionType: TransactionType, year: Int, month: Int): CategoryReport? {

        validateDate(year, month)

        val matchedCategories = transactions.filter {
            it.transactionType == transactionType &&
                    it.transactionDate.toLocalYear() == year &&
                    it.transactionDate.toLocalMonth() == month
        }
        if (matchedCategories.isEmpty()) return null

        val financeMap = matchedCategories.groupBy { transaction ->
            transaction.transactionCategory.categoryName
        }.mapValues { (category, associatedTransactions) ->
            associatedTransactions.sumOf { transaction -> transaction.transactionAmount }
        }

        return CategoryReport(
            transactionType = TransactionType.INCOME,
            categories = financeMap,
            total = financeMap.values.sum()
        )
    }

    override fun getMonthReportForASpecificCategory(
        transactionType: TransactionType,
        category: Category,
        year: Int,
        month: Int
    ): Double? {
        validateDate(year, month)
        val matchedCategories: List<Transaction> = transactions.filter {
            transactionType == it.transactionType && it.transactionCategory.categoryName == category.categoryName &&
                    it.transactionDate.toLocalYear() == year &&
                    it.transactionDate.toLocalMonth() == month
        }
        return if (matchedCategories.isEmpty()) null else matchedCategories.sumOf { it.transactionAmount }
    }


    private fun validateDate(year: Int, month: Int) {
        if (year !in 2020..2025) throw IllegalArgumentException(INVALID_YEAR)
        if (month !in 1..12) throw IllegalArgumentException(INVALID_MONTH)
    }

    companion object {
        private const val INVALID_YEAR = "Invalid year, enter year between 2020 and 2025."
        private const val INVALID_MONTH = "Invalid month, month must be between 1 and 12."
        private const val NO_DATA_FOUND_FOR_THAT_MONTH = "No Data Found For That Month."
    }

    private fun Long.toLocalYear(): Int {
        return Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .year
    }

    private fun Long.toLocalMonth(): Int {
        return Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .monthValue
    }


}
