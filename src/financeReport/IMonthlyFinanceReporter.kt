package financeReport

import financeReport.data.CategoryReport
import financeReport.data.MonthReport
import model.Category
import model.TransactionType

interface IMonthlyFinanceReporter {

    fun getMonthReport(year: Int, month: Int): MonthReport?

    fun getMonthTotalIncome(year: Int, month: Int): Double?

    fun getMonthTotalExpenses(year: Int, month: Int): Double?

    fun getMonthNetBalance(year: Int, month: Int): Double?

    fun getMonthReportOfAllCategories(transactionType: TransactionType, year: Int, month: Int): CategoryReport?

    fun getMonthReportForASpecificCategory(
        transactionType: TransactionType,
        category: Category,
        year: Int,
        month: Int
    ): Double?


}