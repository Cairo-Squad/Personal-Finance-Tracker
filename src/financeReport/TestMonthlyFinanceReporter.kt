package financeReport

import financeReport.data.*

fun main() {

    val financeReporter = MonthlyFinanceReporter(
        transactions = listOf(
            Transaction(1, "Salary for March", TransactionType.INCOME, 15000.0, 1712544000000, Category(1, "Salary")),
            Transaction(2, "Freelance Project", TransactionType.INCOME, 3000.0, 1712208400000, Category(2, "Freelance")),
            Transaction(3, "Grocery Shopping", TransactionType.EXPENSE, 2200.0, 1712035600000, Category(3, "Groceries")),
            Transaction(4, "Electricity Bill", TransactionType.EXPENSE, 600.0, 1711949200000, Category(4, "Utilities")),
            Transaction(5, "Bus Tickets", TransactionType.EXPENSE, 150.0, 1711862800000, Category(5, "Transportation")),
            Transaction(6, "Stock Dividend", TransactionType.INCOME, 800.0, 1711776400000, Category(6, "Investments")),
            Transaction(7, "Dining Out", TransactionType.EXPENSE, 350.0, 1711690000000, Category(7, "Entertainment")),
            Transaction(8, "Water Bill", TransactionType.EXPENSE, 200.0, 1711603600000, Category(4, "Utilities")),
            Transaction(9, "Movie Night", TransactionType.EXPENSE, 100.0, 1711517200000, Category(7, "Entertainment")),
            Transaction(10, "Rent", TransactionType.EXPENSE, 5000.0, 1711430800000, Category(8, "Rent")),
            Transaction(11, "Bonus", TransactionType.INCOME, 2000.0, 1711344400000, Category(1, "Salary")),
            Transaction(12, "Part-time Job", TransactionType.INCOME, 1800.0, 1711258000000, Category(2, "Freelance")),
            Transaction(13, "Taxi", TransactionType.EXPENSE, 90.0, 1711171600000, Category(5, "Transportation")),
            Transaction(14, "Internet Bill", TransactionType.EXPENSE, 350.0, 1711085200000, Category(4, "Utilities")),
            Transaction(15, "Book Sale", TransactionType.INCOME, 500.0, 1710998800000, Category(9, "Side Hustle"))
        )
    )

    //-------------------------------Test Getting Month Report---------------------------------
    /***            Test getMonthReport() Function
     * Invalid year input (throws IllegalArgumentException).
     * Invalid month input (throws IllegalArgumentException).
     * Valid input but no data found (throws NoSuchElementException).
     * Valid input with data exists (returns correct Float value).
     */
    testFunction(
        testDescription = "Given a not valid year, when validating, Then IllegalArgumentException is thrown",
        actualResult = financeReporter.getMonthReport(-1, 1),
        expectedResult = IllegalArgumentException("Invalid Year")
    )
    testFunction(
        testDescription = "Given a not valid month, when validating, Then IllegalArgumentException is thrown",
        actualResult = financeReporter.getMonthReport(2024, -1),
        expectedResult = IllegalArgumentException("Invalid Month")
    )

    testFunction(
        testDescription = "Given a valid year and a valid month but no data found, when validating, Then NoSuchElementException is thrown",
        actualResult = financeReporter.getMonthReport(2024, 12),
        expectedResult = NoSuchElementException("No Data Found For That Month.")
    )
    testFunction(
        testDescription = "Given a valid (year & month) and data exists, when validating, Then Return MothReport object",
        actualResult = financeReporter.getMonthReport(2024, 4),
        expectedResult = MonthReport("2024/4", 15.4, 12.4, 3.4)
    )


    /***            Test getTotalExpenses Function
     * Invalid year input (throws IllegalArgumentException).
     * Invalid month input (throws IllegalArgumentException).
     * Valid input but no data found (throws NoSuchElementException).
     * Valid input with data exists (returns correct Float value).
     */
    //-------------------------------Test Getting Total Expenses--------------------------------
    testFunction(
        testDescription = "Given a not valid year, when validating, Then IllegalArgumentException is thrown",
        actualResult = financeReporter.getMonthTotalExpenses(-1, 1),
        expectedResult = IllegalArgumentException("Invalid Year")
    )
    testFunction(
        testDescription = "Given a not valid month, when validating, Then IllegalArgumentException is thrown",
        actualResult = financeReporter.getMonthTotalExpenses(2024, -1),
        expectedResult = IllegalArgumentException("Invalid Month")
    )

    testFunction(
        testDescription = "Given a valid year and a valid month but no data found, when validating, Then NoSuchElementException is thrown",
        actualResult = financeReporter.getMonthTotalExpenses(2024, 12),
        expectedResult = NoSuchElementException("No data found for the date you specified")
    )
    testFunction(
        testDescription = "Given a valid (year & month) and data exists, when validating, Then Return Double Value",
        actualResult = financeReporter.getMonthTotalExpenses(2024, 12),
        expectedResult = 1.4
    )


    // 1 -------------------------------Test Month Report Income/Expenses Of All categories--------------------------

    testFunction(
        testDescription = "Given wrong year, When validating, Then throw IllegalArgumentException",
        actualResult = financeReporter.getMonthReportOfAllCategories(TransactionType.INCOME, 2020, 4),
        expectedResult = IllegalArgumentException("Invalid Year")
    )
    testFunction(
        testDescription = "Given wrong month, When validating, Then throw IllegalArgumentException",
        actualResult = financeReporter.getMonthReportOfAllCategories(TransactionType.INCOME, 2020, 4),
        expectedResult = IllegalArgumentException("Invalid Month")
    )
    testFunction(
        testDescription = "Given income type but no data found for that month, When validating, Then throw NoSuchElementException",
        actualResult = financeReporter.getMonthReportOfAllCategories(TransactionType.INCOME, 2020, 4),
        expectedResult = NoSuchElementException("No income found for the month you specified")
    )
    testFunction(
        testDescription = "Given expense type but no data found for that month, When validating, Then throw NoSuchElementException",
        actualResult = financeReporter.getMonthReportOfAllCategories(TransactionType.EXPENSE, 2020, 4),
        expectedResult = NoSuchElementException("No expenses found for the month you specified")
    )

    // 2 -------------------------------Test Report Expenses By Category-------------------------
    testFunction(
        testDescription = "Given wrong year, When validating, Then throw IllegalArgumentException",
        actualResult = financeReporter.getMonthReportForASpecificCategory(
            TransactionType.INCOME,
            Category(1, "Swim"),
            2020,
            4
        ),
        expectedResult = IllegalArgumentException("Invalid Year")
    )
    testFunction(
        testDescription = "Given wrong month, When validating, Then throw IllegalArgumentException",
        actualResult = financeReporter.getMonthReportForASpecificCategory(
            TransactionType.INCOME,
            Category(1, "Swim"),
            2020,
            4
        ),
        expectedResult = IllegalArgumentException("Invalid Month")
    )
    testFunction(
        testDescription = "Given income type and category but no data found for that month, When validating, Then throw NoSuchElementException",
        actualResult = financeReporter.getMonthReportForASpecificCategory(
            TransactionType.INCOME,
            Category(1, "Swim"),
            2020,
            4
        ),
        expectedResult = NoSuchElementException("No income found for the month you specified")
    )
    testFunction(
        testDescription = "Given income type and category and data found for that month, When validating, Then return a MonthReport object",
        actualResult = financeReporter.getMonthReportForASpecificCategory(
            TransactionType.INCOME,
            Category(1, "Utilities"),
            2020,
            4
        ), expectedResult = MonthReport("", 3.4, 3.4, 3.4)
    )
}

fun testFunction(
    testDescription: String,
    actualResult: Any,
    expectedResult: Any
) {
    if (actualResult::class == expectedResult::class)
        println("Success -> $testDescription")
    else
        println("Failed -> $testDescription")
}


