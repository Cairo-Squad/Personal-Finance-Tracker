package feature.transaction

import datasource.storage.MemoryStorage
import model.Transaction
import java.time.LocalDateTime


class TransactionManagerImpl(
    private val storage: MemoryStorage
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

    override fun updateTransaction(transaction: Transaction): Boolean {
        val transactionId = transaction.transactionId ?: return false

        val notNullValuesList = listOf(
            transaction.transactionAmount,
            transaction.transactionDescription,
            transaction.transactionCategory,
            transaction.transactionType,
            transaction.transactionDate,
        ).filterNotNull()

        if(notNullValuesList.isEmpty() || notNullValuesList.size > 1) return false

        if(transaction.transactionAmount != null && transaction.transactionAmount < 0 ) return false

        if(transaction.transactionDescription != null && transaction.transactionDescription.isEmpty()) return false

        return storage.updateTransaction(transaction)
    }

    override fun deleteTransaction(transactionId: Int): Boolean {
        val deletedTransaction = storage.getTransactionById(transactionId)
        if (deletedTransaction != null) {
            storage.deleteTransaction(transactionId)
            return true
        }
        return false
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