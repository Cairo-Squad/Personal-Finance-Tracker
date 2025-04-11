package feature.cliController.cliDispatcher

import feature.cliController.ioController.IOController
import feature.transaction.TransactionManager
import model.Transaction
import feature.cliController.CLIConstants
import feature.cliController.UserInputHandler.getAmountInput
import feature.cliController.UserInputHandler.getCategoryInput
import feature.cliController.UserInputHandler.getDateTimeInput
import feature.cliController.UserInputHandler.getDescriptionInput
import feature.cliController.UserInputHandler.getIDInput
import feature.cliController.UserInputHandler.getReportDateInput
import feature.cliController.UserInputHandler.getTransactionTypeInput

class CLIDispatcherImpl(
    private val transactionManager: TransactionManager,
    private val ioController: IOController
) : CLIDispatcher {

    private val commands = mapOf<Int, () -> Unit>(
        CLIConstants.ADD_COMMAND_CODE to ::addTransaction,
        CLIConstants.VIEW_ALL_COMMAND_CODE to ::viewALLTransactions,
        CLIConstants.VIEW_SINGLE_TRANSACTION_COMMAND_CODE to ::viewTransaction,
        CLIConstants.UPDATE_COMMAND_CODE to ::updateTransaction,
        CLIConstants.DELETE_COMMAND_CODE to ::deleteTransaction,
        CLIConstants.MONTHLY_REPORT_COMMAND_CODE to ::getMonthlyReport
    )

    private val editHandlers = mapOf<Int, () -> Boolean>(
        CLIConstants.EDIT_AMOUNT_CODE to ::handleEditAmount,
        CLIConstants.EDIT_DESCRIPTION_CODE to ::handleEditDescription,
        CLIConstants.EDIT_TYPE_CATEGORY_CODE to ::handleEditTypeAndCategory,
        CLIConstants.EDIT_DATE_CODE to ::handleEditDate
    )

    override fun dispatch(userInput: Int) {
        val command = commands[userInput]
        if (command != null) {
            command()
        } else {
            ioController.writeWithNewLine(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    override fun validateOption(option: Int): Boolean {
        return option == CLIConstants.EXIT_COMMAND_CODE || option in commands.keys
    }

    private fun addTransaction() {
        val transactionAmount = getAmountInput(ioController)
        val transactionDescription = getDescriptionInput(ioController)
        val transactionType = getTransactionTypeInput(ioController)
        val transactionCategory = getCategoryInput(ioController, transactionType)
        val transactionDateTime = getDateTimeInput(ioController)

        val transaction = Transaction(
            transactionDescription = transactionDescription,
            transactionType = transactionType,
            transactionAmount = transactionAmount,
            transactionDate = transactionDateTime,
            transactionCategory = transactionCategory
        )

        val isTransactionAdded = transactionManager.addTransaction(transaction)
        if (isTransactionAdded) {
            ioController.writeWithNewLine(CLIConstants.ADD_TRANSACTION_SUCCESS_MESSAGE)
        } else {
            ioController.writeWithNewLine(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    private fun viewTransaction() {
        val transactionID = getIDInput(ioController)
        val transaction = transactionManager.getTransactionById(transactionID)
        if (transaction == null) {
            ioController.writeWithNewLine(CLIConstants.NO_TRANSACTION_WITH_THIS_ID)
        } else {
            ioController.writeWithNewLine(transaction.toString())
        }
    }

    private fun viewALLTransactions() {
        val transactions = transactionManager.getAllTransactions()
        if (transactions.isEmpty()) {
            ioController.writeWithNewLine(CLIConstants.EMPTY_TRANSACTIONS_LIST_MESSAGE)
            return
        }

        ioController.writeWithNewLine(CLIConstants.ALL_TRANSACTIONS_MESSAGE)
        transactions.forEach { transaction ->
            ioController.writeWithNewLine(transaction.toString())
            ioController.write("\n")
        }
    }

    private fun updateTransaction() {
        val transactionId = getIDInput(ioController)
        val transaction = transactionManager.getTransactionById(transactionId)
        if (transaction == null) {
            ioController.writeWithNewLine(CLIConstants.NO_TRANSACTION_WITH_THIS_ID)
            return
        }

        ioController.writeWithNewLine(CLIConstants.TRANSACTION_DETAILS_MESSAGE)
        ioController.writeWithNewLine(transaction.toString())

        while (true) {
            ioController.writeWithNewLine(CLIConstants.UPDATE_OPTIONS)
            ioController.write(CLIConstants.ENTER_EDIT_CHOICE_MESSAGE)
            val editOption = ioController.read()?.toIntOrNull() ?: continue

            if (editOption == CLIConstants.EXIT_EDIT_CODE) break

            val handler = editHandlers[editOption]
            if (handler != null) {
                val isUpdated = handler()
                if (!isUpdated) {
                    ioController.writeWithNewLine(CLIConstants.COMMON_ERROR_MESSAGE)
                }
            }
        }

        ioController.writeWithNewLine(CLIConstants.EDIT_TRANSACTION_SUCCESS_MESSAGE)
    }

    private fun handleEditAmount(): Boolean {
        val newAmount = getAmountInput(ioController)
        return transactionManager.updateTransaction(Transaction(transactionAmount = newAmount))
    }

    private fun handleEditDescription(): Boolean {
        val newDescription = getDescriptionInput(ioController)
        return transactionManager.updateTransaction(Transaction(transactionDescription = newDescription))
    }

    private fun handleEditTypeAndCategory(): Boolean {
        val newType = getTransactionTypeInput(ioController)
        val newCategory = getCategoryInput(ioController, newType)
        return transactionManager.updateTransaction(
            Transaction(
                transactionType = newType,
                transactionCategory = newCategory
            )
        )
    }

    private fun handleEditDate(): Boolean {
        val newDate = getDateTimeInput(ioController)
        return transactionManager.updateTransaction(Transaction(transactionDate = newDate))
    }

    private fun deleteTransaction() {
        val transactionID = getIDInput(ioController)
        val isTransactionDeleted = transactionManager.deleteTransaction(transactionID)
        if (isTransactionDeleted) {
            ioController.writeWithNewLine(CLIConstants.DELETE_TRANSACTION_SUCCESS_MESSAGE)
        } else {
            ioController.writeWithNewLine(CLIConstants.NO_TRANSACTION_WITH_THIS_ID)
        }
    }

    private fun getMonthlyReport() {
        val reportDate = getReportDateInput(ioController)
        val monthlyReport = transactionManager.getReportByMonth(reportDate)
        ioController.writeWithNewLine(monthlyReport.toString())
    }
}