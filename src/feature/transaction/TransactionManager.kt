package feature.transaction

import datasource.Storage
import model.Transaction
import java.time.LocalDateTime

interface TransactionManager {

    fun addTransaction(transaction: Transaction) : Boolean

    fun updateTransaction(transaction: Transaction)

    fun deleteTransaction(transactionId: Int)

    fun getTransactionById(transactionId: Int): Transaction?

    fun getAllTransactions(): List<Transaction>

    fun getReportByMonth(dateTime: LocalDateTime): List<Any>
}