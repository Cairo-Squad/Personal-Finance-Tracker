fun main() {


    val financeReporter = FinanceReporter()

    //-------------------------------Test Getting Month Report---------------------------------
    /***            Test getMonthReport() Function
     * Invalid year input (throws IllegalArgumentException).
     * Invalid month input (throws IllegalArgumentException).
     * Valid input but no data found (throws NoSuchElementException).
     * Valid input with data exists (returns correct Float value).
     */
    testFunction(
        testDescription = "Given a not valid year, when validating, Then IllegalArgumentException is thrown",
        actualResult = financeReporter.getMonthReport(-1,1),
        expectedResult = IllegalArgumentException("Input Not Valid")
    )
    testFunction(
        testDescription = "Given a not valid month, when validating, Then IllegalArgumentException is thrown",
        actualResult = financeReporter.getMonthReport(2024,-1),
        expectedResult = IllegalArgumentException("Input Not Valid")
    )

    testFunction(
        testDescription = "Given a valid year and a valid month but no data found, when validating, Then NoSuchElementException is thrown",
        actualResult = financeReporter.getMonthReport(2024,12),
        expectedResult = NoSuchElementException("No data found for the date you specified")
    )
    testFunction(
        testDescription = "Given a valid (year & month) and data exists, when validating, Then Return Float Value",
        actualResult = financeReporter.getMonthReport(2024,12),
        expectedResult = 1f
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
        actualResult = financeReporter.getMonthTotalExpenses(-1,1),
        expectedResult = IllegalArgumentException("Input Not Valid")
    )
    testFunction(
        testDescription = "Given a not valid month, when validating, Then IllegalArgumentException is thrown",
        actualResult = financeReporter.getMonthTotalExpenses(2024,-1),
        expectedResult = IllegalArgumentException("Input Not Valid")
    )

    testFunction(
        testDescription = "Given a valid year and a valid month but no data found, when validating, Then NoSuchElementException is thrown",
        actualResult = financeReporter.getMonthTotalExpenses(2024,12),
        expectedResult = NoSuchElementException("No data found for the date you specified")
    )
    testFunction(
        testDescription = "Given a valid (year & month) and data exists, when validating, Then Return Float Value",
        actualResult = financeReporter.getMonthTotalExpenses(2024,12),
        expectedResult = 1f
    )


    /***            Test getIncome/ExpensesByCategory() Function
     *Empty category input: Throws IllegalArgumentException.
     * Unsupported category input: Throws IllegalArgumentException.
     * Valid category but no income/expenses data: Throws NoSuchElementException
     * Valid category with income/expenses data: Returns a CategoryReport object with the correct data
     */

    // 1 -------------------------------Test Report Income By Category--------------------------
    testFunction(
        testDescription = "Given an empty category, When validating, Then throw IllegalArgumentException",
        actualResult = financeReporter.getIncomeByCategory(""),
        expectedResult = IllegalArgumentException("Category name cannot be empty or null")
    )
    testFunction(
        testDescription = "Given a not supported category , When validating, Then throw IllegalArgumentException",
        actualResult = financeReporter.getIncomeByCategory("ABCDEF"),
        expectedResult =  IllegalArgumentException("Category Not supported.")
    )
    testFunction(
        testDescription = "Given a valid category with no income data for it, When validating, Then throw NoSuchElementException",
        actualResult = financeReporter.getIncomeByCategory(""),
        expectedResult = NoSuchElementException("No income found for the category you specified")
    )
    testFunction(
        testDescription = "Given a valid category with income data , When validating, Then return CategoryReport object",
        actualResult = financeReporter.getIncomeByCategory(""),
        expectedResult = CategorySummaryReport(CategoryType.Income , category = hashMapOf("Rent" to 200f) , total = 200f)
    )

    // 2 -------------------------------Test Report Expenses By Category-------------------------
    testFunction(
        testDescription = "Given an empty category, When validating, Then throw IllegalArgumentException",
        actualResult = financeReporter.getExpensesByCategory(""),
        expectedResult = IllegalArgumentException("Category name cannot be empty or null")
    )
    testFunction(
        testDescription = "Given a not supported category , When validating, Then throw IllegalArgumentException",
        actualResult = financeReporter.getExpensesByCategory("ABCDEF"),
        expectedResult =  IllegalArgumentException("Category Not supported.")
    )
    testFunction(
        testDescription = "Given a valid category with no income data for it, When validating, Then throw NoSuchElementException",
        actualResult = financeReporter.getExpensesByCategory(""),
        expectedResult = NoSuchElementException("No income found for the category you specified")
    )
    testFunction(
        testDescription = "Given a valid category with income data , When validating, Then return CategoryReport object",
        actualResult = financeReporter.getExpensesByCategory(""),
        expectedResult = CategorySummaryReport(CategoryType.Expenses , category = hashMapOf("Rent" to 200f) , total = 200f)
    )


}


fun testFunction(
    testDescription: String,
    actualResult: Any,
    expectedResult: Any,
) {
    try {
        if (actualResult == expectedResult)
            println("Success -> $testDescription")
        else
            println("Failed -> $testDescription")
    }catch (e:Exception){
        println("Caught Exception -> $e")
    }
    }