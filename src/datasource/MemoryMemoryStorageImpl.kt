package datasource

import model.Transaction

class MemoryMemoryStorageImpl : MemoryStorage {

    private val transactionsList = mutableListOf<Transaction>()

    override fun addTransaction(transaction: Transaction) {
        transactionsList.add(transaction)
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        val index = transactionsList.indexOfFirst { it.transactionId == transaction.transactionId }
        if (index == -1) return false

        transactionsList[index] = transaction
        return true
    }

    override fun deleteTransaction(transactionId: Int) {
        val index = transactionsList.indexOfFirst { it.transactionId == transactionId }
        if (index != -1) {
            transactionsList.removeAt(index)
        }
    }

    override fun getTransactionById(transactionId: Int):Transaction? {
        return transactionsList.find { it.transactionId == transactionId }
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactionsList
    }

    override fun getReportByMonth(month: String): List<Transaction> {
//        val newList=transactionsList.filter { transaction ->
//            val parts = transaction.transactionDate.split("/") // dd/mm/yy ask them
//            if (parts.size == 3) {
//                val transactionMonth = parts[1].toIntOrNull()
//                transactionMonth == month
//            } else {
//                false
//            }
//        }

        return transactionsList
    }

    override fun getNewTransactionId(): Int {
        TODO("Not yet implemented")
    }
}