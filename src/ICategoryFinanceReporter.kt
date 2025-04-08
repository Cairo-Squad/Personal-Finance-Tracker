interface ICategoryFinanceReporter {


    fun getIncomeByCategory(category: String): Float

    fun getExpensesByCategory(category: String): Float
}