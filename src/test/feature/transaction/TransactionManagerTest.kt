package test.feature.transaction

import datasource.Storage
import model.Category
import model.Transaction
import model.TransactionType
import test.storage.StorageMock
import test.util.test

fun main() {

    val storageMock: Storage = StorageMock()
    val transactionManager = TransactionManagerMock(storageMock)

    val initialTransaction = Transaction(
        transactionId = null,
        transactionDescription = null,
        transactionType = null,
        transactionAmount = null,
        transactionDate = null,
        transactionCategory = null
    )
    test(
        name = "Given a transaction with all values null, when validating, then it should return false",
        result = transactionManager.updateTransaction(initialTransaction),
        correctResult = false
    )

    val transaction1 = initialTransaction.copy(transactionId = 1)
    test(
        name = "Given a transaction with only id is not null, when validating, then it should return false",
        result = transactionManager.updateTransaction(transaction1),
        correctResult = false
    )

    val transaction2 = initialTransaction.copy(
        transactionId = 1,
        transactionAmount = 200.0,
        transactionDescription = "new description"
    )
    test(
        name = "Given a transaction with more than one field to edit, when validating, then it should return false",
        result = transactionManager.updateTransaction(transaction2),
        correctResult = false
    )

    val transaction3 = initialTransaction.copy(
        transactionId = 1,
        transactionAmount = -500.0
    )
    test(
        name = "Given a transaction amount with negative number, when validating, then it should return false",
        result = transactionManager.updateTransaction(transaction3),
        correctResult = false
    )

    val transaction4 = initialTransaction.copy(
        transactionId = 1,
        transactionDescription = ""
    )
    test(
        name = "Given a transaction description with empty string, when validating, then it should return false",
        result = transactionManager.updateTransaction(transaction4),
        correctResult = false
    )

    val transaction5 = initialTransaction.copy(
        transactionId = 1,
        transactionAmount = 500.0
    )
    test(
        name = "Given a valid transaction input, when validating, then it should return true",
        result = transactionManager.updateTransaction(transaction5),
        correctResult = true
    )
}

class TransactionManagerMock(
    private val storage: Storage
) {

    fun addTransaction(transaction: Transaction) {

    }

    fun updateTransaction(transaction: Transaction): Boolean {
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



fun runCheckAddTransaction() {

    test(
        "Valid transaction should return true",
        addTransaction(
            "Salary",
            TransactionType.INCOME,
            Category(1, "Salary"),
            2000.0,
            "2025",
            "04",
            "09"
        ),
        true
    )

    test(
        "Invalid year less than 1900 should return false",
        addTransaction(
            "Freelance",
            TransactionType.INCOME,
            Category(2, "Freelance"),
            500.0,
            "1800",
            "04",
            "09"
        ),
        false
    )

    test(
        "Invalid year less than 1900 should return false",
        addTransaction(
            "Freelance",
            TransactionType.INCOME,
            Category(2, "Freelance"),
            500.0,
            "1800",
            "04",
            "09"
        ),
        false
    )

    test(
        "Invalid month should return false",
        addTransaction(
            "Shopping",
            TransactionType.EXPENSE,
            Category(3, "Shopping"),
            100.0,
            "2025",
            "13",
            "09"
        ),
        false
    )

    test(
        "Invalid day should return false",
        addTransaction(
            "Rent",
            TransactionType.EXPENSE,
            Category(4, "Rent"),
            800.0,
            "2025",
            "04",
            "31"
        ),
        false
    )

    test(
        "Negative amount should return false",
        addTransaction(
            "Grocery",
            TransactionType.EXPENSE,
            Category(5, "Grocery"),
            -50.0,
            "2025",
            "04",
            "09"
        ),
        false
    )


    test(
        "Empty description should return false",
        addTransaction(
            "",
            TransactionType.INCOME,
            Category(8, "Other"),
            150.0,
            "2025",
            "04",
            "09"
        ),
        false
    )

}

fun addTransaction(s: String, income: Any, category: Any, d: Double, s1: String, s2: String, s3: String): Boolean {
    return true
}
