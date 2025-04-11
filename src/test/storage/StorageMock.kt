package test.storage

import datasource.storage.MemoryStorage
import model.Transaction
import java.time.LocalDate

class StorageMock(private val transactionList: MutableList<Transaction>): MemoryStorage {
    override fun addTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction):Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        return transactionList.find { it.transactionId == transactionId }
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactionList
    }

    override fun getReportByMonth(localDate : LocalDate): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getNewTransactionId(): Int {
        TODO("Not yet implemented")
    }
}