package datasource

import model.Transaction

interface MemoryStorage {
    fun addTransaction(transaction: Transaction)
    fun updateTransaction(transaction: Transaction): Boolean
    fun deleteTransaction(transactionId: Int)
    fun getTransactionById(transactionId: Int):Transaction?
    fun getAllTransactions(): List<Transaction>
    fun getReportByMonth(month: String): List<Transaction>
    fun getNewTransactionId(): Int
}

