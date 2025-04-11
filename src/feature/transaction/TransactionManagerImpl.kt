package feature.transaction

import datasource.MemoryStorage
import model.Transaction
import java.time.LocalDateTime

class TransactionManagerImpl(
    private val memoryStorage: MemoryStorage
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

        return memoryStorage.updateTransaction(transaction)
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        return memoryStorage.getTransactionById(transactionId)
    }

    override fun getAllTransactions(): List<Transaction> {
        return memoryStorage.getAllTransactions()
    }

    override fun getReportByMonth(dateTime: LocalDateTime): List<Any> {
        TODO("Not yet implemented")
    }

}