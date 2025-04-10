package test.data_source


import data_source.Storage
import model.Category
import model.Transaction
import model.TransactionType
import test.util.test
import java.time.LocalDate

fun main() {

    val storageMock: Storage = StorageMock()
    val transaction = Transaction(
        transactionId = 0,
        transactionType = TransactionType.INCOME,
        transactionDate = LocalDate.now(),
        transactionAmount = 0.0,
        transactionCategory = Category(0, ""),
        transactionDescription = ""
    )

    test(
        name = "Given a transaction that does not exist, when validating, then it should return false",
        result = storageMock.updateTransaction(transaction),
        correctResult = false
    )

    test(
        name = "Given a transaction that exists, when validating, then it should return true",
        result = storageMock.updateTransaction(transaction),
        correctResult = true
    )
}

class StorageMock : Storage {

    private val transactions = emptyList<Transaction>()

    override fun addTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        return false
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(transactionId: Int): Transaction {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getReportByMonth(month: String): List<Transaction> {
        TODO("Not yet implemented")
    }
}