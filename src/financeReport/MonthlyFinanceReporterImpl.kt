package financeReport

import financeReport.data.*
import model.Category
import model.Transaction
import model.TransactionType
import java.time.LocalDateTime

class MonthlyFinanceReporterImpl(private val transactions: List<Transaction>) : MonthlyFinanceReporter {


    override fun getMonthReport(transactionDate: LocalDateTime): MonthReport {
        return MonthReport(
            date = transactionDate,
            income = getMonthTotalIncome(
                transactionDate
            ) ?: 0.0,
            expenses = getMonthTotalExpenses(transactionDate) ?: 0.0,
        )
    }

    override fun getMonthTotalIncome(transactionDate: LocalDateTime): Double? {

        val matchedTransactions = transactions.filter {
            it.transactionType!! == TransactionType.INCOME &&
                    it.transactionDate == transactionDate
        }

        return if (matchedTransactions.isEmpty()) null
        else matchedTransactions.sumOf { it.transactionAmount!! }
    }

    override fun getMonthTotalExpenses(transactionDate: LocalDateTime): Double? {

        val matchedTransactions: List<Transaction> = transactions.filter {
            it.transactionType == TransactionType.EXPENSE &&
                    it.transactionDate == transactionDate
        }

        return if (matchedTransactions.isEmpty()) null
        else matchedTransactions.sumOf { it.transactionAmount ?: 0.0 }
    }

    override fun getMonthNetBalance(transactionDate: LocalDateTime): Double? {

        val matchedTransactions: List<Transaction> = transactions.filter {
            it.transactionDate == transactionDate
        }

        return if (matchedTransactions.isEmpty()) null
        else return matchedTransactions.sumOf {
            if (it.transactionType == TransactionType.INCOME) it.transactionAmount ?: 0.0 else (it.transactionAmount
                ?: 0.0) * -1
        }
    }


    override fun getMonthReportOfAllCategories(
        transactionType: TransactionType,
        transactionDate: LocalDateTime
    ): CategoryReport? {


        val matchedCategories = transactions.filter {
            it.transactionType == transactionType &&
                    it.transactionDate == transactionDate
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

    override fun getMonthReportForASpecificCategory(
        transactionType: TransactionType,
        category: Category,
        transactionDate: LocalDateTime
    ): Double? {
        val matchedCategories: List<Transaction> = transactions.filter {
            transactionType == it.transactionType && it.transactionCategory?.categoryName == category.categoryName
        }
        return if (matchedCategories.isEmpty()) null else matchedCategories.sumOf { it.transactionAmount ?: 0.0 }
    }


}
