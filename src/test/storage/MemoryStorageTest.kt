package test.storage

import datasource.storage.MemoryStorage
import datasource.storage.MemoryStorageImp
import model.Category
import model.Transaction
import model.TransactionType
import test.util.test
import java.time.LocalDateTime


fun main() {

    runCheckGetTransactions()
    runCheckAddTransaction()
    runCheckUpdateTransaction()
    runCheckDeleteTransaction()
    runCheckGetTransactionById()
    runCheckGetAllTransactions()


}
fun runCheckAddTransaction() {
    val storage = MemoryStorageImp

    // Valid addition
    test(
        "Given a valid transaction, when calling addTransaction, Then it should be added to the storage",
        run {
            val transaction = Transaction(
                transactionId = storage.getNewTransactionId(),
                transactionDescription = "Salary",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate =  LocalDateTime.now(),
                transactionCategory = Category(1, "Salary")
            )
            storage.addTransaction(transaction)
            storage.getTransactionById(transaction.transactionId!!) == transaction
        },
        true
    )


}

fun runCheckUpdateTransaction() {
    val storage = MemoryStorageImp

    // Update a valid transaction test
    test(
        "Given an existing transaction, when calling updateTransaction, Then it should update the transaction details",
        run {
            val transaction = Transaction(
                transactionId = 1,
                transactionDescription = "Updated Salary",
                transactionType = TransactionType.INCOME,
                transactionAmount = 3000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1, "Updated Salary")
            )
            storage.updateTransaction(transaction)
        },
        true
    )

    // Update non-existent transaction test
    test(
        "Given a non-existent transaction, when calling updateTransaction, Then it should return false",
        storage.updateTransaction(
            Transaction(
                transactionId = 999,
                transactionDescription = "Non-existent",
                transactionType = TransactionType.INCOME,
                transactionAmount = 3000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(3, "None")
            )
        ),
        false
    )
}

fun runCheckDeleteTransaction() {
    val storage = MemoryStorageImp

    // Valid deletion test
    test(
        "Given an existing transaction ID, when calling deleteTransaction, Then it should be removed",
        run {
            storage.deleteTransaction(1)
            storage.getTransactionById(1) == null
        },
        true
    )


}

fun runCheckGetTransactionById() {
    val storage = MemoryStorageImp


    // Non-existent ID
    test(
        "Given a non-existent transaction ID, when calling getTransactionById,Then it should return null",
        storage.getTransactionById(999) ?: "null",
        "null"
    )
}

fun runCheckGetAllTransactions() {
    val storage = MemoryStorageImp

    // Empty list
    test(
        "Given an empty transaction list, when calling getAllTransactions,Then it should return an empty list",
        storage.getAllTransactions().isEmpty(),
        true
    )

    // Non-empty list
    test(
        "Given a list with transactions, when calling getAllTransactions, it should return all transactions",
        run {
            val transaction1 = Transaction(
                transactionId = 1,
                transactionDescription = "Salary",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = LocalDateTime.now(),
                transactionCategory = Category(1, "Salary")
            )

            val transaction2 = Transaction(
                transactionId = 2,
                transactionDescription = "Rent",
                transactionType = TransactionType.EXPENSE,
                transactionAmount = 800.0,
                transactionDate =  LocalDateTime.now(),
                transactionCategory = Category(2, "Housing")
            )

            storage.addTransaction(transaction1)
            storage.addTransaction(transaction2)

            storage.getAllTransactions().size == 2
        },
        true
    )
}


fun runCheckGetTransactions(){
    //region getTransactionById()
    // Empty storage
    run {
        val storage =StorageMock(mutableListOf<Transaction>())
        test(
            name = "Given an empty list of transactions, when call getTransactionById() it should return null",
            result = storage.getTransactionById(1) ?: "null",
            correctResult = "null"
        )
    }

    // Storage with one transaction with matching ID
    run {
        val list = mutableListOf<Transaction>()
        val storage =StorageMock(list)
        val transaction = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = LocalDateTime.now(),
            transactionCategory = Category(1, "food")
        )
        storage.addTransaction(transaction)

        test(
            name = "Given a list with one transaction, when call getTransactionById() with matching ID it should return the transaction",
            result = storage.getTransactionById(1) ?: "null",
            correctResult = transaction
        )
    }

    // Storage with one transaction with non-matching ID
    run {
        val storage =StorageMock(mutableListOf<Transaction>())

        val transaction = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = LocalDateTime.now(),
            transactionCategory = Category(1, "food")
        )
        storage.addTransaction(transaction)

        test(
            name = "Given a list with one transaction, when call getTransactionById() with non-matching ID it should return null",
            result = storage.getTransactionById(2) ?: "null",
            correctResult = "null"
        )
    }


    // Storage with multiple transactions, one matching
    run {
        val storage =StorageMock(mutableListOf<Transaction>())
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
        storage.addTransaction(transaction1)
        storage.addTransaction(transaction2)
        storage.addTransaction(transaction3)

        test(
            name = "Given a list with multiple transactions, when call getTransactionById() with matching ID it should return correct transaction",
            result = storage.getTransactionById(2) ?: "null",
            correctResult = transaction2
        )
    }

    // Storage with multiple transactions, none matching
    run {
        val storage =StorageMock(mutableListOf<Transaction>())
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
        storage.addTransaction(transaction1)
        storage.addTransaction(transaction2)
        storage.addTransaction(transaction3)

        test(
            name = "Given a list with multiple transactions, when call getTransactionById() with non-matching ID it should return null",
            result = storage.getTransactionById(4) ?: "null",
            correctResult = "null"
        )
    }
    //endregion

    println("----------------------------------------------------------")

    // region getAllTransactions()
    // return an empty list
    run {

        val storage =StorageMock(mutableListOf<Transaction>())
        test(
            name = "Given an empty list, when call getAllTransaction then should its size equal to zero",
            result = storage.getAllTransactions().size,
            correctResult = 0
        )
    }

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


        val storage =StorageMock(mutableListOf<Transaction>())

        storage.addTransaction(transaction1)
        storage.addTransaction(transaction2)
        storage.addTransaction(transaction3)
        test(
            name = "Given an empty list, when return the list then should return the size of the list",
            result = storage.getAllTransactions().size,
            correctResult = 3
        )
    }

    //return a non-empty list
    //endregion
}


class TransactionManagerMock(
    private val storage: MemoryStorage
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




