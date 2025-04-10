package financeReport

import financeReport.data.CategoryReport
import financeReport.data.MonthReport
import model.Category
import model.TransactionType
import java.time.LocalDateTime

interface MonthlyFinanceReporter {

    fun getMonthReport(transactionDate: LocalDateTime): MonthReport?

    fun getMonthIncome(transactionDate:LocalDateTime): Double?

    fun getMonthExpenses(transactionDate:LocalDateTime): Double?

    fun getMonthNetBalance(transactionDate:LocalDateTime): Double?

    fun getMonthReportOfAllCategories(transactionType: TransactionType, transactionDate:LocalDateTime): CategoryReport?

    fun getMonthReportForCategory(
        transactionType: TransactionType,
        category: Category,
        transactionDate:LocalDateTime
    ): Double?


}