package feature.cliController.cliDispatcher

import feature.cliController.ioController.IOController
import feature.transaction.TransactionManager
import model.Category
import model.Transaction
import model.TransactionType
import test.util.Constants
import java.time.LocalDateTime

// TODO: Implement!!
class CLIDispatcherImpl(
    private val transactionManager: TransactionManager,
    private val ioController: IOController
) : CLIDispatcher {

    private val commands = mapOf<Int, () -> Unit>(
        1 to ::addTransaction,
        2 to ::viewTransaction,
        3 to ::updateTransaction,
        4 to ::deleteTransaction,
        5 to ::getSummary
    )

    override fun dispatch(userInput: Int) {
        val command = commands[userInput]
        if (command != null) {
            command()
        } else {
            ioController.writeWithNewLine(Constants.INVALID_COMMAND_MESSAGE)
        }
    }

    private fun addTransaction() {
        // Inputs:-
        // Type
        // Category
        // Amount
        // Description
        // Date & Time
        // TODO: Complete!!
        val transaction = Transaction()
        transactionManager.addTransaction(transaction)
    }

    private fun viewTransaction() {
        // Inputs:-
        // ID
        // TODO: Complete!!
        val transaction = transactionManager.getTransactionById(1)
        ioController.writeWithNewLine(transaction.toString())
    }

    private fun updateTransaction() {
        // Inputs:-
        // ID
        // Type
        // Category
        // Amount
        // Description
        // Date & Time
        // TODO: Complete!!
        transactionManager.updateTransaction(Transaction())
    }

    private fun deleteTransaction() {
        // Inputs:-
        // ID
        // TODO: Complete!!

    }

    private fun getSummary() {
        // Inputs:-
        // Date & Time
        // TODO: Complete!!
    }


    private fun getTransactionTypeInput(): TransactionType {
        // TODO: Complete!!
        return TransactionType.INCOME
    }

    private fun getCategoryInput(): Category {
        // TODO: Complete!!
        return Category(1, "")
    }

    private fun getAmountInput(): Double {
        // TODO: Complete!!
        return 0.0
    }

    fun getDescriptionInput(): String {
        // TODO: Complete!!
        return ""
    }

    fun getDateTimeInput(): LocalDateTime {
        // TODO: Complete!!
        return LocalDateTime.now()
    }
}