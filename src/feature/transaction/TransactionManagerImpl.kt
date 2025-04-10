package feature.transaction

import datasource.Storage
import model.Transaction

class TransactionManagerImpl(
    private val storage: Storage
) : TransactionManager {

    override fun addTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getReportByMonth(month: String): List<Transaction> {
        TODO("Not yet implemented")
    }
}