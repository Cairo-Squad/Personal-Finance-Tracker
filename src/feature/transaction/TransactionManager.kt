package feature.transaction

import datasource.Storage
import model.Category
import model.Transaction
import model.TransactionType
import java.util.*

class TransactionManager(
    private val storage: Storage
) {

    fun addTransaction(
        description: String,
        transactionType: TransactionType,
        transactionCategory: Category,
        amount: Double,
        year: String,
        month: String,
        day: String,
    ) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year.toInt())
            set(Calendar.MONTH, month.toInt() - 1)
            set(Calendar.DAY_OF_MONTH, day.toInt())
        }
        val dateInMillis = calendar.timeInMillis
        val transaction = Transaction(
            transactionId = storage.getNewTransactionId(),
            transactionDescription = description,
            transactionType = transactionType,
            transactionAmount = amount,
            transactionDate = dateInMillis,
            transactionCategory = transactionCategory,
        )

        storage.addTransaction(transaction)
    }

    fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun deleteTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun getTransactionById(transactionId: Int) {
        TODO("Not yet implemented")
    }

    fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    fun getReportByMonth(month: String): List<Transaction> {
        TODO("Not yet implemented")
    }


}