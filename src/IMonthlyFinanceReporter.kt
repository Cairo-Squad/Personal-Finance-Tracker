import src.FinancialSummaryReport

interface IMonthlyFinanceReporter {

    fun getMonthReport(year: Int, month: Int): FinancialSummaryReport

    fun getMonthTotalIncome(year: Int, month: Int): Float

    fun getMonthTotalExpenses(year: Int, month: Int): Float

    fun getMonthNetBalance(year: Int, month: Int): Float

}