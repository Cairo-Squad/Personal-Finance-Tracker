//package financeReport
//
//import financeReport.data.*
//import model.Category
//import model.Transaction
//import model.TransactionType
//import java.time.LocalDateTime
//
//fun main() {
//    val rentCategory = Category(1, "Rent")
//    val groceriesCategory = Category(2, "Groceries")
//    val salaryCategory = Category(3, "Salary")
//    val entertainmentCategory = Category(4, "Entertainment")
//    val freelanceCategory = Category(5, "Freelance")
//    val utilitiesCategory = Category(6, "Utilities")
//    val transportCategory = Category(7, "Transportation")
//    val transactions = listOf(
//        Transaction(
//            1,
//            "April Rent",
//            TransactionType.EXPENSE,
//            5000.0,
//            LocalDateTime.of(2024, 4, 1, 10, 0),
//            rentCategory
//        ),
//        Transaction(
//            2,
//            "Grocery Shopping",
//            TransactionType.EXPENSE,
//            1200.0,
//            LocalDateTime.of(2024, 4, 3, 12, 30),
//            groceriesCategory
//        ),
//        Transaction(
//            3,
//            "April Salary",
//            TransactionType.INCOME,
//            12000.0,
//            LocalDateTime.of(2024, 4, 5, 9, 0),
//            salaryCategory
//        ),
//        Transaction(
//            4,
//            "Netflix Subscription",
//            TransactionType.EXPENSE,
//            250.0,
//            LocalDateTime.of(2024, 4, 7, 20, 0),
//            entertainmentCategory
//        ),
//        Transaction(
//            5,
//            "Freelance Project",
//            TransactionType.INCOME,
//            3000.0,
//            LocalDateTime.of(2024, 4, 10, 18, 15),
//            freelanceCategory
//        ),
//        Transaction(
//            6,
//            "Electricity Bill",
//            TransactionType.EXPENSE,
//            700.0,
//            LocalDateTime.of(2024, 4, 12, 14, 0),
//            utilitiesCategory
//        ),
//        Transaction(
//            7,
//            "Bus Fare",
//            TransactionType.EXPENSE,
//            100.0,
//            LocalDateTime.of(2024, 4, 13, 8, 0),
//            transportCategory
//        ),
//        Transaction(
//            8,
//            "Water Bill",
//            TransactionType.EXPENSE,
//            300.0,
//            LocalDateTime.of(2024, 4, 15, 15, 0),
//            utilitiesCategory
//        ),
//        Transaction(
//            9,
//            "Bonus Payment",
//            TransactionType.INCOME,
//            1500.0,
//            LocalDateTime.of(2024, 4, 18, 11, 30),
//            salaryCategory
//        ),
//        Transaction(
//            10,
//            "Taxi Ride",
//            TransactionType.EXPENSE,
//            85.0,
//            LocalDateTime.of(2024, 4, 20, 22, 0),
//            transportCategory
//        ),
//        Transaction(
//            11,
//            "Movie Night",
//            TransactionType.EXPENSE,
//            150.0,
//            LocalDateTime.of(2024, 4, 21, 19, 0),
//            entertainmentCategory
//        ),
//        Transaction(
//            12,
//            "April Groceries",
//            TransactionType.EXPENSE,
//            1000.0,
//            LocalDateTime.of(2024, 4, 23, 17, 45),
//            groceriesCategory
//        ),
//        Transaction(
//            13,
//            "Freelance Blog",
//            TransactionType.INCOME,
//            1200.0,
//            LocalDateTime.of(2024, 4, 24, 16, 0),
//            freelanceCategory
//        ),
//        Transaction(
//            14,
//            "Internet Bill",
//            TransactionType.EXPENSE,
//            400.0,
//            LocalDateTime.of(2024, 4, 25, 13, 0),
//            utilitiesCategory
//        ),
//        Transaction(
//            15,
//            "Grocery Top-up",
//            TransactionType.EXPENSE,
//            250.0,
//            LocalDateTime.of(2024, 4, 28, 10, 30),
//            groceriesCategory
//        )
//    )
//
//    val financeReporter = MonthlyFinanceReporterImpl(transactions)
//
//    testFunction(
//        testDescription = "Given a valid year and a valid month but no data found, when validating, Then return MonthReport with empty values",
//        actualResult = financeReporter.getMonthReport(LocalDateTime.of(2025, 4, 21, 19, 0)),
//        expectedResult = MonthReport(LocalDateTime.of(2025, 4, 21, 19, 0), 0.0, 0.0, 0.0)
//    )
//    testFunction(
//        testDescription = "Given a valid (year & month) and data exists, when validating, Then Return MothReport object",
//        actualResult = financeReporter.getMonthReport(LocalDateTime.of(2024, 4, 21, 19, 0)),
//        expectedResult = MonthReport(LocalDateTime.of(2024, 4, 21, 19, 0), 15.4, 12.4, 3.4)
//    )
//
//
//    testFunction(
//        testDescription = "Given a valid date but no data found, when validating, Then null is returned",
//        actualResult = financeReporter.getMonthExpenses(LocalDateTime.of(2025, 4, 21, 19, 0)),
//        expectedResult = MonthReport(LocalDateTime.of(2025, 4, 21, 19, 0),0.0,0.0)
//    )
//    testFunction(
//        testDescription = "Given a valid (year & month) and data exists, when validating, Then Return Double Value",
//        actualResult = financeReporter.getMonthExpenses(LocalDateTime.of(2024, 4, 21, 19, 0)),
//        expectedResult = 1.4
//    )
//
//
//    // 1 -------------------------------Test Month Report Income/Expenses Of All categories--------------------------
//
//    testFunction(
//        testDescription = "Given income type but no data found for that month, When validating, Then return null",
//        actualResult = financeReporter.getMonthReportOfAllCategories(
//            TransactionType.INCOME,
//            LocalDateTime.of(2024, 4, 21, 19, 0)
//        ),
//        expectedResult = null
//    )
//    testFunction(
//        testDescription = "Given expense type but no data found for that month, When validating, Then return null",
//        actualResult = financeReporter.getMonthReportOfAllCategories(
//            TransactionType.EXPENSE,
//            LocalDateTime.of(2025, 4, 21, 19, 0)
//        ),
//        expectedResult = null
//    )
//
//    // 2 -------------------------------Test Report Expenses By Category-------------------------
//
//    testFunction(
//        testDescription = "Given income type and category but no data found for that month, When validating, Then return null",
//        actualResult = financeReporter.getMonthReportForCategory(
//            TransactionType.INCOME,
//            Category(1, "Swim"),
//            LocalDateTime.of(2025, 4, 21, 19, 0)
//        ),
//        expectedResult = null
//    )
//    testFunction(
//        testDescription = "Given income type and category and data found for that month, When validating, Then return a MonthReport object",
//        actualResult = financeReporter.getMonthReportForCategory(
//            TransactionType.INCOME,
//            Category(1, "Utilities"),
//            requiredReportDate = LocalDateTime.of(2024, 4, 21, 19, 0),
//        ), expectedResult = MonthReport(LocalDateTime.of(2024, 4, 21, 19, 0), 3.4, 3.4, 3.4)
//    )
//}
//
//fun testFunction(
//    testDescription: String,
//    actualResult: Any?,
//    expectedResult: Any?
//) {
//    val sameType = actualResult != null && expectedResult != null &&
//            actualResult::class == expectedResult::class
//
//    val status = if (sameType) "✅ Success" else "❌ Failed"
//    println("$status -> $testDescription")
//}
//
//
