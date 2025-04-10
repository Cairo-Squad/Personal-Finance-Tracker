package test.storage

import datasource.MemoryStorage
import model.Category
import model.Transaction
import model.TransactionType
import java.time.LocalDateTime


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
            transactionDate = LocalDateTime.now(),
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
            transactionDate = LocalDateTime.now(),
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

import datasource.Storage
import model.Category
import model.Transaction
import model.TransactionType
import test.storage.StorageMock
import test.util.test
import java.time.LocalDateTime

//package test.storage
//fun main(){
//    // valid id test
//    check(
//        name = "given valid id , when checked, then should return true",
//        result = checkID(id : Int),
//        correctResult = true
//    )
//    //empty id test
//    check(
//        name = "given empty id , when checked, then should return false",
//        result =checkID(id :Int),
//        correctResult = false
//    )
//    //out of range id
//    check(
//        name = "given out of range id , when checked, then should return false",
//        result =checkID(id : Int),
//        correctResult = false
//    )
//
//
//
//}
//fun check(name: String, result:Boolean , correctResult:Boolean) {
//    if (result == correctResult) {
//        println("Success - $name")
//    } else {
//        println("Failed - $name")
//    }
//}

fun check(name: String, result: Any, expectedResult: Any) {
    if (result == expectedResult) {
        println("Success - $name")
    } else {
        println("Failure - $name")
    }
}

fun main() {



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
