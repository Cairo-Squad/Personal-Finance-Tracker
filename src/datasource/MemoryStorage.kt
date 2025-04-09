package datasource

import model.Transaction

class MemoryStorage : Storage {

    val list = mutableListOf<Transaction>()

    override fun addTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(transactionId: Int):Transaction? {
        return null
    }

    override fun getAllTransactions(): List<Transaction> {
        return emptyList()
    }

    override fun getReportByMonth(month: String): List<Transaction> {
        TODO("Not yet implemented")
    }
}