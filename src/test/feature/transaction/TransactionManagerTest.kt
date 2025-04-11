package test.feature.transaction


import datasource.storage.MemoryStorage
import datasource.storage.MemoryStorageImp
import feature.transaction.TransactionManagerImpl
import model.Category
import model.Transaction
import model.TransactionType
import test.storage.StorageMock
import test.util.test
import java.time.LocalDateTime

fun main() {


    val initialTransaction = Transaction(
        transactionId = null,
        transactionDescription = null,
        transactionType = null,
        transactionAmount = null,
        transactionDate = null,
        transactionCategory = null
    )

    val list = mutableListOf(initialTransaction)
    val storageMock: MemoryStorage = StorageMock(list)
    val transactionManager = TransactionManagerMock(storageMock)


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
    // endregion

    runCheckGetTransactions()

}

class TransactionManagerMock(
    private val storage: MemoryStorage
) {

    fun addTransaction(transaction: Transaction) {

    }

    fun updateTransaction(transaction: Transaction): Boolean {
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
        val fakeMemoryStorage = StorageMock(emptyList)
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

        val fakeMemoryStorage = StorageMock(list)
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
        val fakeMemoryStorage = StorageMock(list)
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

        val fakeMemoryStorage = StorageMock(list)
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

        val fakeMemoryStorage = StorageMock(list)
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
        val fakeMemoryStorage = StorageMock(emptyList)
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

        val list = mutableListOf(transaction1, transaction2, transaction3)

        val fakeMemoryStorage = StorageMock(list)
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage)

        list.add(transaction1)
        list.add(transaction2)
        list.add(transaction3)
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
