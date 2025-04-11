package datasource.storage

import model.Transaction
import java.time.LocalDate

object MemoryStorageImp : MemoryStorage {

    private val transactionList = mutableListOf<Transaction>()

    override fun addTransaction(transaction: Transaction) :Boolean{
        transactionList.add(transaction)
        return true
    }

    override fun updateTransaction(transaction: Transaction) :Boolean{
        val index = transactionList.indexOfFirst { it.transactionId == transaction.transactionId }
        if (index != -1) {
            transactionList[index] = transaction
            return true
        }
        return false
    }
    override fun deleteTransaction(transactionId: Int) {
        val index = transactionList.indexOfFirst { it.transactionId == transactionId }
        if (index != -1) {
            transactionList.removeAt(index)
        }
    }

    override fun getTransactionById(transactionId: Int):Transaction? {
        return transactionList.find { it.transactionId == transactionId }
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactionList
    }

    override fun getReportByMonth(localDate: LocalDate): List<Transaction> {
        return transactionList.filter { transaction ->
            transaction.transactionDate?.year == localDate.year && transaction.transactionDate?.monthValue == localDate.monthValue
        }
    }

    override fun getNewTransactionId(): Int {
       return transactionList.size + 1


    }
}