package feature.financeReport

import feature.financeReport.data.*
import model.Category
import model.Transaction
import model.TransactionType
import java.time.LocalDateTime

class MonthlyFinanceReporterImpl : MonthlyFinanceReporter {


    /***
     * Method to get total income, expense and netBalance of a specefic month.
     *
     * @param requiredReportDate util.date of month that the user want a report for.
     *
     * @return return Object of type MonthReport that contains the util.date and total income,expense and netBalance.
     *
     */
    override fun getMonthReport(transactions: List<Transaction>, requiredReportDate: LocalDateTime): MonthReport {
        return MonthReport(
            date = requiredReportDate,
            income = getMonthIncome(
                transactions,
                requiredReportDate
            ) ?: 0.0,
            expenses = getMonthExpenses(transactions, requiredReportDate) ?: 0.0,
        )
    }

    /***
     * Method to get month total Income.
     *
     * @param requiredReportDate required util.date to search for.
     *
     * @return Double value if data exists, if no data found then returns null.
     */
    private fun getMonthIncome(transactions: List<Transaction>, requiredReportDate: LocalDateTime): Double? {
        val matchedTransactions = transactions.filter {
            it.transactionType!! == TransactionType.INCOME && checkDateMatching(it.transactionDate, requiredReportDate)
        }

        return if (matchedTransactions.isEmpty()) null
        else matchedTransactions.sumOf { it.transactionAmount!! }
    }


    /***
     * Method to get month total Expense.
     *
     * @param requiredReportDate required util.date to search for.
     *
     * @return Double value if data exists, if no data found then returns null.
     */
    private fun getMonthExpenses(transactions: List<Transaction>, requiredReportDate: LocalDateTime): Double? {

        val matchedTransactions: List<Transaction> = transactions.filter {
            it.transactionType == TransactionType.EXPENSE && checkDateMatching(it.transactionDate, requiredReportDate)
        }

        return if (matchedTransactions.isEmpty()) null
        else matchedTransactions.sumOf { it.transactionAmount ?: 0.0 }
    }

    /***
     * Method to get month netBalance (Income - Expense).
     *
     * @param requiredReportDate required util.date to search for.
     *
     * @return Double value if data exists, if no data found then returns null.
     */
    private fun getMonthNetBalance(transactions: List<Transaction>, requiredReportDate: LocalDateTime): Double? {

        val matchedTransactions: List<Transaction> = transactions.filter {
            checkDateMatching(it.transactionDate, requiredReportDate)
        }

        return if (matchedTransactions.isEmpty()) null
        else return matchedTransactions.sumOf {
            if (it.transactionType == TransactionType.INCOME) it.transactionAmount ?: 0.0 else (it.transactionAmount
                ?: 0.0) * -1
        }
    }


    override fun getMonthReportOfAllCategories(
        transactions: List<Transaction>,
        transactionType: TransactionType,
        requiredReportDate: LocalDateTime
    ): CategoryReport? {


        val matchedCategories = transactions.filter {
            it.transactionType == transactionType && checkDateMatching(it.transactionDate, requiredReportDate)
        }
        if (matchedCategories.isEmpty()) return null

        val financeMap = matchedCategories.groupBy { transaction ->
            transaction.transactionCategory?.categoryName!!
        }.mapValues { (category, associatedTransactions) ->
            associatedTransactions.sumOf { transaction -> transaction.transactionAmount ?: 0.0 }
        }

        return CategoryReport(
            transactionType = TransactionType.INCOME,
            categories = financeMap,
            total = financeMap.values.sum()
        )
    }

    override fun getMonthReportForCategory(
        transactions: List<Transaction>,
        transactionType: TransactionType,
        category: Category,
        requiredReportDate: LocalDateTime
    ): Double? {
        val matchedCategories: List<Transaction> = transactions.filter {
            transactionType == it.transactionType && it.transactionCategory?.categoryName == category.categoryName
        }
        return if (matchedCategories.isEmpty()) null
        else matchedCategories.sumOf { it.transactionAmount ?: 0.0 }
    }


    private fun checkDateMatching(transactionDate: LocalDateTime?, requiredReportDate: LocalDateTime) =
        transactionDate?.year == requiredReportDate.year &&
                transactionDate.month == requiredReportDate.month


}
