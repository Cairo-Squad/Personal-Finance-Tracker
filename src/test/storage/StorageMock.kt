<<<<<<<< HEAD:src/test/feature/transaction/FakeMemoryStorage.kt
package test.feature.transaction
========
package test.storage
>>>>>>>> 436dd8c9bcdc501eae1188b95f942ca2cff81c5f:src/test/storage/StorageMock.kt

import datasource.Storage
import model.Transaction

<<<<<<<< HEAD:src/test/feature/transaction/FakeMemoryStorage.kt
class FakeMemoryStorage(private val list:MutableList<Transaction>): Storage {
========
class StorageMock: Storage {
>>>>>>>> 436dd8c9bcdc501eae1188b95f942ca2cff81c5f:src/test/storage/StorageMock.kt
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
<<<<<<<< HEAD:src/test/feature/transaction/FakeMemoryStorage.kt
        return list.find { it.transactionId == transactionId }
========
        TODO("Not yet implemented")
>>>>>>>> 436dd8c9bcdc501eae1188b95f942ca2cff81c5f:src/test/storage/StorageMock.kt
    }

    override fun getAllTransactions(): List<Transaction> {
        return list
    }

    override fun getReportByMonth(month: String): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getNewTransactionId(): Int {
        TODO("Not yet implemented")
    }
}