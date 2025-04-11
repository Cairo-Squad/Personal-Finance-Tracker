package feature.transaction

import feature.financeReport.data.MonthReport
import model.Transaction
import java.time.LocalDateTime

interface TransactionManager {

    fun addTransaction(transaction: Transaction) : Boolean

    fun updateTransaction(transaction: Transaction): Boolean

    fun deleteTransaction(transactionId: Int):Boolean

    fun getTransactionById(transactionId: Int): Transaction?

    fun getAllTransactions(): List<Transaction>

    fun getReportByMonth(dateTime: LocalDateTime): MonthReport
}