package feature.transaction

import datasource.Storage
import model.Transaction
import java.time.LocalDateTime

class TransactionManagerImpl(
    private val storage: Storage
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
        if(transaction.transactionId == null) return false
        if(transaction.transactionAmount == null
            && transaction.transactionDescription == null
            && transaction.transactionType == null
            && transaction.transactionCategory == null
            && transaction.transactionDate == null
        ) {
            return false
        }

        var counter = 0
        if(transaction.transactionAmount != null ) counter++
        if(transaction.transactionDescription != null ) counter++
        if(transaction.transactionDate != null ) counter++
        if(transaction.transactionType != null ) counter++
        if(transaction.transactionCategory != null ) counter++

        if(counter > 1) return false

        when{
            transaction.transactionAmount != null && transaction.transactionAmount < 0 -> return false
            transaction.transactionDescription != null && transaction.transactionDescription.isEmpty() -> return false
        }

        return true
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getReportByMonth(dateTime: LocalDateTime): List<Any> {
        TODO("Not yet implemented")
    }

}