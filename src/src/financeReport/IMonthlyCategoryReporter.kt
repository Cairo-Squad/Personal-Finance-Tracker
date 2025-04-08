package src.financeReport

interface IMonthlyCategoryReporter {

    fun getMonthIncomeByCategory(category: String, year:Int, month:Int): Float

    fun getMonthExpensesByCategory(category: String, year:Int, month:Int): Float
}