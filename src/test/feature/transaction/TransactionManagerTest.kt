package test.feature.transaction

import datasource.Storage
import datasource.MemoryStorage
import feature.transaction.TransactionManagerImpl
import model.Category
import model.Transaction
import model.TransactionType
import test.data_source.StorageMock
import test.util.test
import java.time.LocalDateTime

fun main() {

    val storageMock: Storage = StorageMock()
    val transactionManager = TransactionManagerMock(storageMock)

    val validTransaction = Transaction(
        transactionId = 1,
        transactionDescription = "old description",
        transactionType = TransactionType.EXPENSE,
        transactionAmount = 20.0,
        transactionDate = LocalDateTime.now(),
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

    runCheckGetTransactions()

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
        return storage.getAllTransactions().find { it.transactionId == transactionId }
    }

    fun getAllTransactions(): List<Transaction> {
        return storage.getAllTransactions()
    }

    fun getReportByMonth(month: String): List<Transaction> {
        return emptyList()
    }

}

fun runCheckGetTransactions(){
    // region getTransactionById()
    run {
        val emptyList = mutableListOf<Transaction>()
        val fakeMemoryStorage = FakeMemoryStorage(emptyList)
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage)
        test(
            name = "Given an empty list of transactions, when call getTransactionById() it should return null",
            result = transactionManager.getTransactionById(1) ?: "null",
            correctResult = "null"
        )
    }

    // Storage with one transaction with matching ID
    run {
        val transaction = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = LocalDateTime.now(),
            transactionCategory = Category(1,"food")
        )

        val list = mutableListOf(transaction)

        val fakeMemoryStorage = FakeMemoryStorage(list)
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage)

        test(
            name = "Given a list with one transaction, when call getTransactionById() with matching ID it should return the transaction",
            result = transactionManager.getTransactionById(1) ?: "null",
            correctResult = transaction
        )
    }

    // Storage with one transaction with non-matching ID
    run {
        val list = mutableListOf(
            Transaction(
                transactionId = 1,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1,"food")
            ),
        )
        val fakeMemoryStorage = FakeMemoryStorage(list)
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage)

        test(
            name = "Given a list with one transaction, when call getTransactionById() with non-matching ID it should return null",
            result = transactionManager.getTransactionById(2) ?: "null",
            correctResult = "null"
        )
    }


    // Storage with multiple transactions, one matching
    run {
        val list = mutableListOf(
            Transaction(
                transactionId = 1,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1,"food")
            ),
            Transaction(
                transactionId = 2,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1,"food")
            ),
            Transaction(
                transactionId = 3,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1,"food")
            )
        )

        val fakeMemoryStorage = FakeMemoryStorage(list)
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage)

        test(
            name = "Given a list with multiple transactions, when call getTransactionById() with matching ID it should return correct transaction",
            result = transactionManager.getTransactionById(2) ?: "null",
            correctResult = Transaction(
                transactionId = 2,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1,"food")
            )
        )
    }

    // Storage with multiple transactions, none matching
    run {
        val list = mutableListOf(
            Transaction(
                transactionId = 1,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1,"food")
            ),
            Transaction(
                transactionId = 2,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1,"food")
            ),
            Transaction(
                transactionId = 3,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1,"food")
            )
        )

        val fakeMemoryStorage = FakeMemoryStorage(list)
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage)

        test(
            name = "Given a list with multiple transactions, when call getTransactionById() with non-matching ID it should return null",
            result = transactionManager.getTransactionById(4) ?: "null",
            correctResult = "null"
        )
    }
    //endregion

    println("----------------------------------------------------------")

    // region getAllTransactions()
    // return an empty list
    run {
        val emptyList = mutableListOf<Transaction>()
        val fakeMemoryStorage = FakeMemoryStorage(emptyList)
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage)
        test(
            name = "Given an empty list, when call getAllTransaction then should its size equal to zero",
            result = transactionManager.getAllTransactions().size,
            correctResult = 0
        )
    }


    // return non-empty list
    run {
        val transaction1 = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = LocalDateTime.now(),
            transactionCategory = Category(1, "food")
        )
        val transaction2 = Transaction(
            transactionId = 2,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = LocalDateTime.now(),
            transactionCategory = Category(1, "food")
        )
        val transaction3 = Transaction(
            transactionId = 3,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = LocalDateTime.now(),
            transactionCategory = Category(1, "food")
        )


        val fakeMemoryStorage = MemoryStorage()
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage)

        fakeMemoryStorage.addTransaction(transaction1)
        fakeMemoryStorage.addTransaction(transaction2)
        fakeMemoryStorage.addTransaction(transaction3)
        test(
            name = "Given an empty list, when return the list then should return the list",
            result = transactionManager.getAllTransactions(),
            correctResult = fakeMemoryStorage.getAllTransactions()
        )
    }

    //return a non-empty list
    //endregion
}



fun runCheckAddTransaction() {
    val storageMock: Storage = MemoryStorage()
    val transactionManager = TransactionManagerImpl(storageMock)


    test(
        "Amount is null should return false",
        transactionManager.addTransaction(
            Transaction(
                null,
                "Bonus",
                TransactionType.INCOME,
                null,
                LocalDateTime.now(),
                Category(1, "Salary")
            )
        ),
        false
    )
    test(
        "Valid transaction → should return true",
        transactionManager.addTransaction(
            Transaction(
                transactionId = null,
                transactionDescription = "Salary",
                transactionType = TransactionType.INCOME,
                transactionAmount = 3000.0,
                transactionDate = LocalDateTime.of(2025, 4, 10, 0, 0),
                transactionCategory = Category(1, "Income")
            )
        ),
        true
    )

    test(
        "Amount is null → should return false",
        transactionManager.addTransaction(
            Transaction(
                transactionId = null,
                transactionDescription = "Bonus",
                transactionType = TransactionType.INCOME,
                transactionAmount = null,
                transactionDate = LocalDateTime.of(2025, 4, 10, 0, 0),
                transactionCategory = Category(1, "Salary")
            )
        ),
        false
    )

    test(
        "Amount is zero → should return false",
        transactionManager.addTransaction(
            Transaction(
                transactionId = null,
                transactionDescription = "Refund",
                transactionType = TransactionType.EXPENSE,
                transactionAmount = 0.0,
                transactionDate = LocalDateTime.of(2025, 4, 10, 0, 0),
                transactionCategory = Category(2, "Other")
            )
        ),
        false
    )

    test(
        "Amount is negative → should return false",
        transactionManager.addTransaction(
            Transaction(
                transactionId = null,
                transactionDescription = "Fine",
                transactionType = TransactionType.EXPENSE,
                transactionAmount = -100.0,
                transactionDate = LocalDateTime.of(2025, 4, 10, 0, 0),
                transactionCategory = Category(3, "Penalty")
            )
        ),
        false
    )

    test(
        "Description is null → should return false",
        transactionManager.addTransaction(
            Transaction(
                transactionId = null,
                transactionDescription = null,
                transactionType = TransactionType.INCOME,
                transactionAmount = 500.0,
                transactionDate = LocalDateTime.of(2025, 4, 10, 0, 0),
                transactionCategory = Category(4, "Freelance")
            )
        ),
        false
    )

    test(
        "Description is blank → should return false",
        transactionManager.addTransaction(
            Transaction(
                transactionId = null,
                transactionDescription = "   ",
                transactionType = TransactionType.EXPENSE,
                transactionAmount = 100.0,
                transactionDate = LocalDateTime.of(2025, 4, 10, 0, 0),
                transactionCategory = Category(5, "Shopping")
            )
        ),
        false
    )

    test(
        "Transaction type is null → should return false",
        transactionManager.addTransaction(
            Transaction(
                transactionId = null,
                transactionDescription = "Pay",
                transactionType = null,
                transactionAmount = 1000.0,
                transactionDate = LocalDateTime.of(2025, 4, 10, 0, 0),
                transactionCategory = Category(6, "Payment")
            )
        ),
        false
    )

    test(
        "Transaction category is null → should return false",
        transactionManager.addTransaction(
            Transaction(
                transactionId = null,
                transactionDescription = "Lunch",
                transactionType = TransactionType.EXPENSE,
                transactionAmount = 50.0,
                transactionDate = LocalDateTime.of(2025, 4, 10, 0, 0),
                transactionCategory = null
            )
        ),
        false
    )

    test(
        "Transaction date is null → should return false",
        transactionManager.addTransaction(
            Transaction(
                transactionId = null,
                transactionDescription = "Dinner",
                transactionType = TransactionType.EXPENSE,
                transactionAmount = 75.0,
                transactionDate = null,
                transactionCategory = Category(7, "Food")
            )
        ),
        false
    )

}

