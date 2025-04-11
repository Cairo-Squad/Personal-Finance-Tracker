package test.feature.transaction

import datasource.storage.MemoryStorage
import model.Transaction
import java.time.LocalDate

class FakeMemoryStorage(private val list:MutableList<Transaction>): MemoryStorage {
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
        return list.find { it.transactionId == transactionId }
    }

    override fun getAllTransactions(): List<Transaction> {
        return list
    }

    override fun getReportByMonth(localDate : LocalDate): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getNewTransactionId(): Int {
        TODO("Not yet implemented")
    }
}