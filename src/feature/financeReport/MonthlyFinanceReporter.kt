package feature.financeReport

import feature.financeReport.data.CategoryReport
import feature.financeReport.data.MonthReport
import model.Category
import model.Transaction
import model.TransactionType
import java.time.LocalDateTime

interface MonthlyFinanceReporter {

    fun getMonthReport(transactions: List<Transaction>, requiredReportDate: LocalDateTime): MonthReport

//    fun getMonthIncome(requiredReportDate:LocalDateTime): Double?
//
//    fun getMonthExpenses(requiredReportDate:LocalDateTime): Double?

//    fun getMonthNetBalance(requiredReportDate:LocalDateTime): Double?

    fun getMonthReportOfAllCategories(
        transactions: List<Transaction>,
        transactionType: TransactionType, requiredReportDate: LocalDateTime
    ): CategoryReport?

    fun getMonthReportForCategory(
        transactions: List<Transaction>,
        transactionType: TransactionType,
        category: Category,
        requiredReportDate: LocalDateTime
    ): Double?


}