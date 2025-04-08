package src.financeReport

import src.financeReport.data.MonthReport

interface IMonthlyFinanceReporter {

    fun getMonthReport(year: Int, month: Int): MonthReport

    fun getMonthTotalIncome(year: Int, month: Int): Float

    fun getMonthTotalExpenses(year: Int, month: Int): Float

    fun getMonthNetBalance(year: Int, month: Int): Float

}