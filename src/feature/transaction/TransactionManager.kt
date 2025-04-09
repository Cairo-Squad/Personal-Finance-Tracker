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

    fun getTransactionById(transactionId: Int){
        TODO("Not yet implemented")
    }

    fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    fun getReportByMonth(month: String): List<Transaction>{
        TODO("Not yet implemented")
    }


}