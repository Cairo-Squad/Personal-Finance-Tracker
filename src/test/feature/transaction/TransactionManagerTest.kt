package test.feature.transaction

import data_source.Storage
import model.Category
import model.Transaction
import model.TransactionType
import test.data_source.StorageMock
import test.util.test
import java.time.LocalDate

fun main() {

    val storageMock: Storage = StorageMock()
    val transactionManager = TransactionManagerMock(storageMock)

    val validTransaction = Transaction(
        transactionId = 1,
        transactionDescription = "old description",
        transactionType = TransactionType.EXPENSE,
        transactionAmount = 20.0,
        transactionDate = LocalDate.of(2025, 1, 1),
        transactionCategory = Category(1, "Food")
    )

    // region transaction not exists
    val result1 = transactionManager.updateTransaction(
        transactionId = "1",
        transactionDescription = "New description",
        transactionType = "EXPENSE",
        transactionAmount = "100.0",
        transactionDate = "2025-04-09",
        transactionCategory = "Food"
    )

    test(
        name = "Given a transaction does not exists, when validating, then return false",
        result = result1,
        correctResult = false
    )
    // endregion

    // region transaction id
    val result2 = transactionManager.updateTransaction(
        transactionId = "",
        transactionDescription = "New description",
        transactionType = "EXPENSE",
        transactionAmount = "-100.0",
        transactionDate = "2025-04-09",
        transactionCategory = "Food"
    )

    test(
        name = "Given an empty id, when validating, then return false",
        result = result2,
        correctResult = false
    )

    val result3 = transactionManager.updateTransaction(
        transactionId = "a",
        transactionDescription = "New description",
        transactionType = "EXPENSE",
        transactionAmount = "-100.0",
        transactionDate = "2025-04-09",
        transactionCategory = "Food"
    )

    test(
        name = "Given id with non-numeric values, when validating, then return false",
        result = result3,
        correctResult = false
    )
    // endregion

    // region transaction amount
    val result4 = transactionManager.updateTransaction(
        transactionId = "1",
        transactionDescription = "New description",
        transactionType = "EXPENSE",
        transactionAmount = "aa",
        transactionDate = "2025-04-09",
        transactionCategory = "Food"
    )

    test(
        name = "Given amount with characters, when validating, then return false",
        result = result4,
        correctResult = false
    )
    // endregion

    // region transaction type
    val result5 = transactionManager.updateTransaction(
        transactionId = "1",
        transactionDescription = "Description",
        transactionType = "Food",
        transactionAmount = "50.0",
        transactionDate = "2025-04-09",
        transactionCategory = "Food"
    )

    test(
        name = "Given an invalid type, when validating, then return false",
        result = result5,
        correctResult = false
    )
    // endregion

    // region transaction date

    // endregion

    // region transaction Category

    // endregion

}

class TransactionManagerMock(
    private val storage: Storage
) {

    fun addTransaction(transaction: Transaction) {

    }

    fun updateTransaction(
        transactionId: String,
        transactionDescription: String,
        transactionType: String,
        transactionAmount: String,
        transactionDate: String,
        transactionCategory: String
    ): Boolean {
        return false
    }

    fun deleteTransaction(transaction: Transaction) {

    }

    fun getTransactionById(transactionId: Int): Transaction? {
        return null
    }

    fun getAllTransactions(): List<Transaction> {
        return emptyList()
    }

    fun getReportByMonth(month: String): List<Transaction> {
        return emptyList()
    }

}