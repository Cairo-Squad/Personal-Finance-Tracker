package feature.transaction

import datasource.Storage
import model.Transaction
import java.time.LocalDateTime

class TransactionManagerImpl(
    private val storage: Storage
) : TransactionManager {


    override fun addTransaction(transaction: Transaction):Boolean {

        if (transaction.transactionAmount == null || transaction.transactionAmount <= 0) return false
        if (transaction.transactionDescription.isNullOrBlank()) return false
        if (transaction.transactionCategory == null) return false
        if (transaction.transactionType == null) return false
        if (transaction.transactionDate == null) return false

        val newTransaction = transaction.copy(transactionId = storage.getNewTransactionId())

       return storage.addTransaction(newTransaction)
    }

    override fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        return storage.getTransactionById(transactionId)
    }

    override fun getAllTransactions(): List<Transaction> {
        return storage.getAllTransactions()
    }

    override fun getReportByMonth(dateTime: LocalDateTime): List<Any> {
        TODO("Not yet implemented")
    }

}