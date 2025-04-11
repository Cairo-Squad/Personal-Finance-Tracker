package test.feature.transaction

import feature.financeReport.MonthlyFinanceReporter
import feature.financeReport.data.CategoryReport
import feature.financeReport.data.MonthReport
import model.Category
import model.Transaction
import model.TransactionType
import java.time.LocalDateTime

class MonthlyReporterMock: MonthlyFinanceReporter {
    override fun getMonthReport(transactions: List<Transaction>, requiredReportDate: LocalDateTime): MonthReport {
        return MonthReport(
            date = LocalDateTime.now(),
            income = 0.0,
            expenses = 0.0,
            netBalance = 0.0
        )
    }

    override fun getMonthReportOfAllCategories(
        transactions: List<Transaction>,
        transactionType: TransactionType,
        requiredReportDate: LocalDateTime
    ): CategoryReport? {
        return null
    }

    override fun getMonthReportForCategory(
        transactions: List<Transaction>,
        transactionType: TransactionType,
        category: Category,
        requiredReportDate: LocalDateTime
    ): Double? {
        return null
    }
}