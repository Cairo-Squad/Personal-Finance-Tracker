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
