package feature.transaction

import datasource.Storage
import model.Transaction
import java.time.LocalDateTime

class TransactionManagerImpl(
    private val storage: Storage
) : TransactionManager {

    override fun addTransaction(transaction: Transaction) {
        val newTransaction = transaction.copy(transactionId = storage.getNewTransactionId())
        storage.addTransaction(newTransaction)
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