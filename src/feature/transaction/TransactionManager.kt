package feature.transaction

import model.Transaction

interface TransactionManager {

    fun addTransaction(transaction: Transaction)

    fun updateTransaction(transaction: Transaction)

    fun deleteTransaction(transaction: Transaction)

    fun getTransactionById(transactionId: Int)

    fun getAllTransactions(): List<Transaction>

    fun getReportByMonth(month: String): List<Transaction>
}