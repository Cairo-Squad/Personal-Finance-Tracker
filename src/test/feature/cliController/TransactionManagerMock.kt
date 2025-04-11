package test.feature.cliController

import feature.financeReport.data.MonthReport
import feature.transaction.TransactionManager
import model.Category
import model.Transaction
import model.TransactionType
import java.time.LocalDateTime

class TransactionManagerMock : TransactionManager {
    override fun addTransaction(transaction: Transaction): Boolean {
        return true
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        return true
    }

    override fun deleteTransaction(transactionId: Int): Boolean {
        return true
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        return Transaction(
            transactionId = 1,
            transactionDescription = "Test description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 20_000.0,
            transactionDate = LocalDateTime.now(),
            transactionCategory = Category(1, "Salary")
        )
    }

    override fun getAllTransactions(): List<Transaction> {
        return listOf(
            Transaction(
                transactionId = 1,
                transactionDescription = "Test description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 20_000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1, "Salary")
            ),
            Transaction(
                transactionId = 2,
                transactionDescription = "Test description",
                transactionType = TransactionType.EXPENSE,
                transactionAmount = 1_000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(2, "Food")
            )
        )
    }

    override fun getReportByMonth(dateTime: LocalDateTime): MonthReport {
        return MonthReport(
            date = LocalDateTime.now(),
            income = 0.0,
            expenses = 0.0,
            netBalance = 0.0
        )
    }
}