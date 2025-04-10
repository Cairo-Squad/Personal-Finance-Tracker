package datasource

import model.Transaction

interface Storage {
    fun addTransaction(transaction: Transaction): Boolean
    fun updateTransaction(transaction: Transaction)
    fun deleteTransaction(transactionId: Int)
    fun getTransactionById(transactionId: Int):Transaction?
    fun getAllTransactions(): List<Transaction>
    fun getReportByMonth(month: String): List<Transaction>
    fun getNewTransactionId(): Int
}

