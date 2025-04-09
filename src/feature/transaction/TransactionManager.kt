package feature.transaction

import data_source.Storage
import model.Category
import model.Transaction
import model.TransactionType
import java.time.LocalDate

class TransactionManager(
    private val storage: Storage
) {

    fun addTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun updateTransaction(
        transactionId: String,
        transactionDescription: String,
        transactionType: String,
        transactionAmount: String,
        transactionDate: String,
        transactionCategory: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    fun deleteTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    fun getTransactionById(transactionId: Int): Transaction? {
        TODO("Not yet implemented")
    }

    fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    fun getReportByMonth(month: String): List<Transaction> {
        TODO("Not yet implemented")
    }

}

/*

Edit Transaction
Feature: Update a Transaction
Responsibilities:
    Ask for transaction ID
    Display the current details of the transaction
    Prompt user to select which field to update
    Validate and apply the changes
    Ensure the updated transaction is saved in the correct position
    Write tests

*/











