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
    runCheckDeleteTransaction()

    runUpdateTransactionTests()

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
    val monthlyReporter = MonthlyReporterMock()
    // region getTransactionById()
    run {
        val emptyList = mutableListOf<Transaction>()
        val fakeMemoryStorage = StorageMock(emptyList)
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage, monthlyReporter)
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
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage, monthlyReporter)

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
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage, monthlyReporter)

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
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage, monthlyReporter)

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
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage, monthlyReporter)

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
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage, monthlyReporter)
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
        val transactionManager = TransactionManagerImpl(fakeMemoryStorage, monthlyReporter)

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
    val storageMock: MemoryStorage = MemoryStorageImp
    val monthlyReporter = MonthlyReporterMock()
    val transactionManager = TransactionManagerImpl(storageMock, monthlyReporter)


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
fun runCheckDeleteTransaction() {
    val monthlyReporter = MonthlyReporterMock()

    //region transactionExist
    run {
        val transaction = Transaction(
            transactionId = 1,
            transactionDescription = "Lunch",
            transactionType = TransactionType.EXPENSE,
            transactionAmount = 50.0,
            transactionDate = LocalDateTime.now(),
            transactionCategory = Category(1, "Food")
        )
        val list = mutableListOf(transaction)
        val fakeStorage = StorageMock(list)
        val manager = TransactionManagerImpl(fakeStorage, monthlyReporter)

        test(
            name = "Given existing transaction, when deleted, should return true",
            result = manager.deleteTransaction(1),
            correctResult = true
        )

        test(
            name = "After deletion, getTransactionById should return null",
            result = manager.getTransactionById(1) ?: "null",
            correctResult = "null"
        )
    }
    //endregion

    //region transactionnotexist
    run {
        val list = mutableListOf<Transaction>()
        val fakeStorage = StorageMock(list)
        val manager = TransactionManagerImpl(fakeStorage, monthlyReporter)

        test(
            name = "Given non-existent transaction, when deleted, should return false",
            result = manager.deleteTransaction(99),
            correctResult = false
        )
    }
//endregion

    //region emptyStorage
    run {
        val fakeStorage = StorageMock(mutableListOf())
        val manager = TransactionManagerImpl(fakeStorage, monthlyReporter)

        test(
            name = "Given empty storage, when delete is called, should return false",
            result = manager.deleteTransaction(1),
            correctResult = false
        )
    }
//endregion

    //region deleteMultipleTransaction
    run {
        val list = mutableListOf(
            Transaction(1, "T1", TransactionType.EXPENSE, 100.0, LocalDateTime.now(), Category(1, "Cat1")),
            Transaction(2, "T2", TransactionType.INCOME, 200.0, LocalDateTime.now(), Category(2, "Cat2"))
        )
        val fakeStorage = StorageMock(list)
        val manager = TransactionManagerImpl(fakeStorage, monthlyReporter)

        test(
            name = "Given multiple transactions, when one is deleted, should return true",
            result = manager.deleteTransaction(1),
            correctResult = true
        )

        test(
            name = "After deletion, deleted transaction should not be found",
            result = manager.getTransactionById(1) ?: "null",
            correctResult = "null"
        )

        test(
            name = "Remaining transaction should still exist",
            result = manager.getTransactionById(2)?.transactionId ?: "null",
            correctResult = 2
        )
    }
    //endregion
    println("----------------------------------------------------------\n")

}

fun runUpdateTransactionTests(){
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
        transactionType = TransactionType.INCOME,
        transactionDescription = "new description"
    )
    test(
        name = "Given a transaction with more than two fields to edit, when validating, then it should return false",
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

