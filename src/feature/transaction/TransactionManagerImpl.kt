package feature.transaction

import datasource.storage.MemoryStorage
import model.Transaction
import java.time.LocalDateTime

class TransactionManagerImpl(
    private val storage: MemoryStorage
) : TransactionManager {

    override fun addTransaction(transaction: Transaction) {
        /*val calendar = Calendar.getInstance().apply {
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

        storage.addTransaction(transaction)*/
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