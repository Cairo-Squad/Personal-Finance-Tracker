package datasource.storage

import model.Transaction
import java.time.LocalDate

interface MemoryStorage {
    fun addTransaction(transaction: Transaction)
    fun updateTransaction(transaction: Transaction)
    fun deleteTransaction(transactionId: Int)
    fun getTransactionById(transactionId: Int):Transaction?
    fun getAllTransactions(): List<Transaction>
    fun getReportByMonth(localDate :LocalDate): List<Transaction>
    fun getNewTransactionId(): Int
}

