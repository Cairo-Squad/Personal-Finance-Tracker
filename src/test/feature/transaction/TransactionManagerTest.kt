package test.feature.transaction

import datasource.MemoryStorage
import feature.transaction.TransactionManager
import model.Category
import model.Transaction
import model.TransactionType

fun main() {

    // region getTransactionById()
    run {
        val emptyList = mutableListOf<Transaction>()
        val fakeMemoryStorage = FakeMemoryStorage(emptyList)
        val transactionManager = TransactionManager(fakeMemoryStorage)
        check(
            name = "Given an empty list of transactions, when call getTransactionById() it should return null",
            result = transactionManager.getTransactionById(1) ?: "null",
            expectedResult = "null"
        )
    }

    // Storage with one transaction with matching ID
    run {
        val transaction = Transaction(
            transactionId = 1,
            transactionDescription = "description",
            transactionType = TransactionType.INCOME,
            transactionAmount = 2000.0,
            transactionDate = 12548,
            transactionCategory = Category(1,"food")
        )

        val list = mutableListOf(transaction)

        val fakeMemoryStorage = FakeMemoryStorage(list)
        val transactionManager = TransactionManager(fakeMemoryStorage)

        check(
            name = "Given a list with one transaction, when call getTransactionById() with matching ID it should return the transaction",
            result = transactionManager.getTransactionById(1) ?: "null",
            expectedResult = transaction
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
                transactionDate = 12548,
                transactionCategory = Category(1,"food")
            ),
        )
        val fakeMemoryStorage = FakeMemoryStorage(list)
        val transactionManager = TransactionManager(fakeMemoryStorage)
        test.storage.check(
            name = "Given a list with one transaction, when call getTransactionById() with non-matching ID it should return null",
            result = transactionManager.getTransactionById(2) ?: "null",
            expectedResult = "null"
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
                transactionDate = 12548,
                transactionCategory = Category(1,"food")
            ),
            Transaction(
                transactionId = 2,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = 12548,
                transactionCategory = Category(1,"food")
            ),
            Transaction(
                transactionId = 3,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = 12548,
                transactionCategory = Category(1,"food")
            )
        )

        val fakeMemoryStorage = FakeMemoryStorage(list)
        val transactionManager = TransactionManager(fakeMemoryStorage)

        test.storage.check(
            name = "Given a list with multiple transactions, when call getTransactionById() with matching ID it should return correct transaction",
            result = transactionManager.getTransactionById(2) ?: "null",
            expectedResult = Transaction(
                transactionId = 2,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = 12548,
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
                transactionDate = 12548,
                transactionCategory = Category(1,"food")
            ),
            Transaction(
                transactionId = 2,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = 12548,
                transactionCategory = Category(1,"food")
            ),
            Transaction(
                transactionId = 3,
                transactionDescription = "description",
                transactionType = TransactionType.INCOME,
                transactionAmount = 2000.0,
                transactionDate = 12548,
                transactionCategory = Category(1,"food")
            )
        )

        val fakeMemoryStorage = FakeMemoryStorage(list)
        val transactionManager = TransactionManager(fakeMemoryStorage)

        test.storage.check(
            name = "Given a list with multiple transactions, when call getTransactionById() with non-matching ID it should return null",
            result = transactionManager.getTransactionById(4) ?: "null",
            expectedResult = "null"
        )
    }
    //endregion

    println("----------------------------------------------------------")

    // region getAllTransactions()
    // return an empty list
    run {
        val emptyList = mutableListOf<Transaction>()
        val fakeMemoryStorage = FakeMemoryStorage(emptyList)
        val transactionManager = TransactionManager(fakeMemoryStorage)
        check(
            name = "Given an empty list, when call getAllTransaction then should its size equal to zero",
            result = transactionManager.getAllTransactions().size,
            expectedResult = 0
        )
    }


    // return non-empty list
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


        val fakeMemoryStorage = MemoryStorage()
        val transactionManager = TransactionManager(fakeMemoryStorage)

        fakeMemoryStorage.list.add(transaction1)
        fakeMemoryStorage.list.add(transaction2)
        fakeMemoryStorage.list.add(transaction3)
        check(
            name = "Given an empty list, when return the list then should return the list",
            result = transactionManager.getAllTransactions(),
            expectedResult = fakeMemoryStorage.list
        )
    }

    //return a non-empty list
    //endregion


}

fun check(name:String, result:Any, expectedResult:Any){
    if (result == expectedResult){
        println("Success - $name")
    }else{
        println("Failure - $name")
    }
}
