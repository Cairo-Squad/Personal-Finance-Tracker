package feature.transaction

import datasource.Storage
import model.Transaction

class TransactionManager(
    private val storage: Storage
) {

    fun addTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun deleteTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun getTransactionById(transactionId: Int):Transaction?{
        return storage.getTransactionById(transactionId)
    }

    fun getAllTransactions(): List<Transaction> {
        return storage.getAllTransactions()
    }

    fun getReportByMonth(month: String): List<Transaction>{
        TODO("Not yet implemented")
    }


}