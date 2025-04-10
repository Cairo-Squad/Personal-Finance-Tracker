package test.storage

import datasource.MemoryStorage
import model.Category
import model.Transaction
import model.TransactionType


fun main() {

    //region getTransactionById()
    // Empty storage
    run {
        val storage = MemoryStorage()
        check(
            name = "Given an empty list of transactions, when call getTransactionById() it should return null",
            result = storage.getTransactionById(1) ?: "null",
            expectedResult = "null"
        )
    }

    // Storage with one transaction with matching ID
    run {
        val storage = MemoryStorage()
        val list = mutableListOf<Transaction>()
        val transaction = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )
        storage.addTransaction(transaction)

        check(
            name = "Given a list with one transaction, when call getTransactionById() with matching ID it should return the transaction",
            result = storage.getTransactionById(1) ?: "null",
            expectedResult = transaction
        )
    }

    // Storage with one transaction with non-matching ID
    run {
        val storage = MemoryStorage()
        val transaction = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )
        storage.addTransaction(transaction)

        check(
            name = "Given a list with one transaction, when call getTransactionById() with non-matching ID it should return null",
            result = storage.getTransactionById(2) ?: "null",
            expectedResult = "null"
        )
    }


    // Storage with multiple transactions, one matching
    run {
        val storage = MemoryStorage()
        val transaction1 = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )

        val transaction2 = Transaction(
            transactionId = 2,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )

        val transaction3 = Transaction(
            transactionId = 3,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )
        storage.addTransaction(transaction1)
        storage.addTransaction(transaction2)
        storage.addTransaction(transaction3)

        check(
            name = "Given a list with multiple transactions, when call getTransactionById() with matching ID it should return correct transaction",
            result = storage.getTransactionById(2) ?: "null",
            expectedResult = transaction2
        )
    }

    // Storage with multiple transactions, none matching
    run {
        val storage = MemoryStorage()
        val transaction1 = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )

        val transaction2 = Transaction(
            transactionId = 2,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )

        val transaction3 = Transaction(
            transactionId = 3,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )
        storage.addTransaction(transaction1)
        storage.addTransaction(transaction2)
        storage.addTransaction(transaction3)

        check(
            name = "Given a list with multiple transactions, when call getTransactionById() with non-matching ID it should return null",
            result = storage.getTransactionById(4) ?: "null",
            expectedResult = "null"
        )
    }
    //endregion

    println("----------------------------------------------------------")

    // region getAllTransactions()
    // return an empty list
    run {

        val storage = MemoryStorage()
        check(
            name = "Given an empty list, when call getAllTransaction then should its size equal to zero",
            result = storage.getAllTransactions().size,
            expectedResult = 0
        )
    }

    run {

        val transaction1 = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )
        val transaction2 = Transaction(
            transactionId = 2,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )
        val transaction3 = Transaction(
            transactionId = 3,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1, "food")
        )


        val storage = MemoryStorage()

        storage.addTransaction(transaction1)
        storage.addTransaction(transaction2)
        storage.addTransaction(transaction3)
        check(
            name = "Given an empty list, when return the list then should return the size of the list",
            result = storage.getAllTransactions().size,
            expectedResult = 3
        )
    }

    //return a non-empty list
    //endregion
}

fun check(name: String, result: Any, expectedResult: Any) {
    if (result == expectedResult) {
        println("Success - $name")
    } else {
        println("Failure - $name")
    }
}