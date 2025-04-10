package datasource

import model.Transaction

class MemoryStorage : Storage {

    private val list = mutableListOf<Transaction>()

    override fun addTransaction(transaction: Transaction) {
        list.add(transaction)
    }

    override fun updateTransaction(transaction: Transaction) {
        val index = list.indexOfFirst { it.transactionId == transaction.transactionId }
        if (index != -1) {
            list[index] = transaction
        }
    }
    override fun deleteTransaction(transactionId: Int) {
        val index = list.indexOfFirst { it.transactionId == transactionId }
        if (index != -1) {
            list.removeAt(index)
        }
    }

    override fun getTransactionById(transactionId: Int):Transaction? {
        return list.find { it.transactionId == transactionId }
    }

    override fun getAllTransactions(): List<Transaction> {
        return list
    }

    override fun getReportByMonth(month: String): List<Transaction> {
//        val newList=list.filter { transaction ->
//            val parts = transaction.transactionDate.split("/") // dd/mm/yy ask them
//            if (parts.size == 3) {
//                val transactionMonth = parts[1].toIntOrNull()
//                transactionMonth == month
//            } else {
//                false
//            }
//        }

        return list
    }

    override fun getNewTransactionId(): Int {
        TODO("Not yet implemented")
    }
}