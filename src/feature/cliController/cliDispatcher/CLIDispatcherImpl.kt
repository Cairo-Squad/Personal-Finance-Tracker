package feature.cliController.cliDispatcher

import feature.cliController.ioController.IOController
import feature.transaction.TransactionManager
import model.Category
import model.Transaction
import model.TransactionType
import test.util.Constants
import java.time.LocalDateTime

class CLIDispatcherImpl(
    private val transactionManager: TransactionManager,
    private val ioController: IOController
) : CLIDispatcher {

    private val commands = mapOf<Int, () -> Unit>(
        1 to ::addTransaction,
        2 to ::viewTransaction,
        3 to ::updateTransaction,
        4 to ::deleteTransaction,
        5 to ::getMonthlyReport
    )

    override fun dispatch(userInput: Int) {
        val command = commands[userInput]
        if (command != null) {
            command()
        } else {
            ioController.writeWithNewLine(Constants.INVALID_COMMAND_MESSAGE)
        }
    }

    // TODO: Call others functions!!
    private fun addTransaction() {
        // Inputs:-
        // Type
        // Category
        // Amount
        // Description
        // Date & Time
        // TODO: Complete!!
//        getTransactionTypeInput()
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

    private fun getMonthlyReport() {
        // Inputs:-
        // Date & Time
        // TODO: Complete!!
    }


    private fun getTransactionTypeInput(): TransactionType {
        ioController.writeWithNewLine(Constants.TRANSACTION_TYPE_INPUT_MESSAGE)

        TransactionType.entries.forEachIndexed { index, type ->
            ioController.write("${index + 1}. ${type.value}\t")
        }
        ioController.write("\n")

        ioController.write(Constants.ENTER_TRANSACTION_TYPE_INPUT_MESSAGE)
        while (true) {
            val userInput = ioController.read()
            val parsedTransactionTypeNumber = userInput?.toIntOrNull()
            if (parsedTransactionTypeNumber == null || parsedTransactionTypeNumber !in 1..TransactionType.entries.size) {
                ioController.write(Constants.INVALID_OPTION_MESSAGE)
            } else {
                return TransactionType.entries[parsedTransactionTypeNumber - 1]
            }
        }
    }

    private fun getCategoryInput(): Category {
        ioController.writeWithNewLine("Select the category you need:")
        // display list of categories
        ioController.write("Enter category number >>> ")
        val userInput = ioController.read()
        // validate
        // if he selects a category, return it directly
        // if he selects other, take data of new category and return it
        // TODO: Complete!!
        return Category(1, "")
    }

    private fun getAmountInput(): Double {
        ioController.write("Enter the amount >>> ")
        val userInput = ioController.read()
        // validate
        // TODO: Complete!!
        return 0.0
    }

    fun getDescriptionInput(): String {
        ioController.write("Enter the amount >>> ")
        val userInput = ioController.read()
        // validate
        // TODO: Complete!!
        return ""
    }

    fun getDateTimeInput(): LocalDateTime {
        ioController.write("Enter the date using format yyyy-mm-dd, e.g. 2002-12-09 >>> ")
        val userInput = ioController.read()
        // validate
        // TODO: Complete!!
        return LocalDateTime.now()
    }

    fun getIDInput(): Int {
        ioController.write("Enter the ID >>> ")
        val userInput = ioController.read()
        // validate
        // TODO: Complete!!
        return 0
    }
}