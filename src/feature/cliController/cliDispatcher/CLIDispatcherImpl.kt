package feature.cliController.cliDispatcher

import feature.cliController.ioController.IOController
import feature.transaction.TransactionManager
import model.Category
import model.Transaction
import model.TransactionType
import feature.cliController.CLIConstants
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class CLIDispatcherImpl(
    private val transactionManager: TransactionManager,
    private val ioController: IOController
) : CLIDispatcher {

    // TODO: Add option to view all the transactions!!!
    private val commands = mapOf<Int, () -> Unit>(
        CLIConstants.ADD_COMMAND_CODE to ::addTransaction,
        CLIConstants.VIEW_ALL_COMMAND_CODE to ::viewALLTransactions,
        CLIConstants.VIEW_SINGLE_TRANSACTION_COMMAND_CODE to ::viewTransaction,
        CLIConstants.UPDATE_COMMAND_CODE to ::updateTransaction,
        CLIConstants.DELETE_COMMAND_CODE to ::deleteTransaction,
        CLIConstants.MONTHLY_REPORT_COMMAND_CODE to ::getMonthlyReport
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

    // region Use Transaction Manager
    private fun addTransaction() {
        val transactionAmount = getAmountInput()
        val transactionDescription = getDescriptionInput()
        val transactionType = getTransactionTypeInput()
        val transactionCategory = getCategoryInput()
        val transactionDateTime = getDateTimeInput()

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
        val transactionID = getIDInput()
        val transaction = transactionManager.getTransactionById(transactionID)
        if (transaction == null) {
            ioController.writeWithNewLine(CLIConstants.COMMON_ERROR_MESSAGE)
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

        transactions.forEach { transaction ->
            ioController.writeWithNewLine(transaction.toString())
            ioController.write("\n")
        }
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
//        transactionManager.updateTransaction(Transaction())
//        ioController.writeWithNewLine(CLIConstants.EDIT_TRANSACTION_SUCCESS_MESSAGE)
    }

    private fun deleteTransaction() {
        val transactionID = getIDInput()
        val isTransactionDeleted = transactionManager.deleteTransaction(transactionID)
        if (isTransactionDeleted) {
            ioController.writeWithNewLine(CLIConstants.DELETE_TRANSACTION_SUCCESS_MESSAGE)
        } else {
            ioController.writeWithNewLine(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    private fun getMonthlyReport() {
        val reportDate = getReportDateInput()
        val monthlyReport = transactionManager.getReportByMonth(reportDate)
        ioController.writeWithNewLine(monthlyReport.toString())
    }
    // endregion

    // region Get User Input
    private fun getTransactionTypeInput(): TransactionType {
        ioController.writeWithNewLine(CLIConstants.TRANSACTION_TYPE_INPUT_MESSAGE)

        TransactionType.entries.forEachIndexed { index, type ->
            ioController.write("${index + 1}. ${type.value}\t")
        }
        ioController.write("\n")

        ioController.write(CLIConstants.ENTER_TRANSACTION_TYPE_INPUT_MESSAGE)
        while (true) {
            val userInput = ioController.read()
            val parsedTransactionTypeNumber = userInput?.toIntOrNull()
            if (parsedTransactionTypeNumber == null || parsedTransactionTypeNumber !in 1..TransactionType.entries.size) {
                ioController.write(CLIConstants.ENTER_VALID_OPTION_MESSAGE)
            } else {
                return TransactionType.entries[parsedTransactionTypeNumber - 1]
            }
        }
    }

    private fun getCategoryInput(): Category {
        // TODO: Complete!!
        ioController.writeWithNewLine("Select the category you need:")
        // display list of categories
        ioController.write("Enter category number >>> ")
        val userInput = ioController.read()
        // validate
        // if he selects a category, return it directly
        // if he selects other, take data of new category and return it
        return Category(1, "")
    }

    private fun getAmountInput(): Double {
        ioController.write(CLIConstants.ENTER_AMOUNT_MESSAGE)
        while (true) {
            val userInput = ioController.read()
            val parsedAmount = userInput?.toDoubleOrNull()
            if (parsedAmount == null || parsedAmount < 0) {
                ioController.write(CLIConstants.ENTER_VALID_AMOUNT_MESSAGE)
            } else {
                return parsedAmount
            }
        }
    }

    private fun getDescriptionInput(): String {
        ioController.write(CLIConstants.ENTER_DESCRIPTION_MESSAGE)
        return ioController.read() ?: ""
    }

    private fun getDateTimeInput(): LocalDateTime {
        val date: LocalDate = getDateInput()
        val time: LocalTime = getTimeInput()
        return LocalDateTime.of(date, time)
    }

    private fun getDateInput(): LocalDate {
        // TODO: Check the validation functions with Galal to use it!!!
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var date: LocalDate? = null

        ioController.write("Enter the date using format (yyyy-MM-dd), e.g. 2002-12-09 >>> ")

        while (date == null) {
            val dateInput = ioController.read() ?: ""

            try {
                date = LocalDate.parse(dateInput, dateFormatter)
            } catch (e: Exception) {
                ioController.write("Please enter a valid date >>> ")
            }
        }

        return date
    }

    private fun getTimeInput(): LocalTime {
        // TODO: Check the validation functions with Galal to use it!!!
        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
        var time: LocalTime? = null

        ioController.write("Enter the time using format (hh:mm a), e.g. 01:33 PM >>> ")

        while (time == null) {
            val timeInput = ioController.read() ?: ""

            try {
                time = LocalTime.parse(timeInput, timeFormatter)
            } catch (e: Exception) {
                ioController.write("Please enter a valid time >>> ")
            }
        }

        return time
    }

    private fun getReportDateInput(): LocalDateTime {
        // TODO: Check the validation functions with Galal to use it!!!
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var date: LocalDate? = null

        ioController.write("Enter the date using format (yyyy-MM), e.g. 2002-12 >>> ")

        while (date == null) {
            val dateInput = ioController.read() ?: ""

            try {
                date = LocalDate.parse("$dateInput-01", dateFormatter)
            } catch (e: Exception) {
                ioController.write("Please enter a valid date >>> ")
            }
        }

        return LocalDateTime.of(date, LocalTime.now())
    }

    private fun getIDInput(): Int {
        ioController.write(CLIConstants.ENTER_ID_MESSAGE)

        while (true) {
            val userInput = ioController.read()
            val parsedID = userInput?.toIntOrNull()
            if (parsedID == null || parsedID < 0) {
                ioController.write(CLIConstants.ENTER_VALID_ID_MESSAGE)
            } else {
                return parsedID
            }
        }
    }

    // endregion
}