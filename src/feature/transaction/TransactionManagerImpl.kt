package feature.transaction

import datasource.Storage
import financeReport.MonthlyFinanceReporter
import financeReport.MonthlyFinanceReporterImpl
import model.Transaction
import java.time.LocalDateTime

class TransactionManagerImpl(
    private val storage: Storage,
) : TransactionManager {

    override fun addTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getReportByMonth(dateTime: LocalDateTime): List<Any> {
        TODO("Not yet implemented")
    }

}