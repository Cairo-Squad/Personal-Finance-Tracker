package test.feature.transaction

import model.Category
import model.TransactionType

fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) println("success : $name")
    else println("fail : $name")
}


fun runCheckAddTransaction() {

    check(
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

    check(
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

    check(
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

    check(
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

    check(
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

    check(
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


    check(
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
