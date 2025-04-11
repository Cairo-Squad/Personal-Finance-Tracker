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

    // TODO: Make these constants!!!!
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
            ioController.writeWithNewLine(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    // region Use Transaction Manager
    // TODO: Call others functions!!
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

        // TODO: refactor this after merging the code!!
//        val isTransactionAdded = transactionManager.addTransaction(transaction)
        val isTransactionAdded = true
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
            // TODO: Format this!!
            ioController.writeWithNewLine(transaction.toString())
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
        transactionManager.updateTransaction(Transaction())
        ioController.writeWithNewLine(CLIConstants.EDIT_TRANSACTION_SUCCESS_MESSAGE)
    }

    private fun deleteTransaction() {
        val transactionID = getIDInput()
        // TODO: Refactor this after merging the code!!
//        val isTransactionDeleted = transactionManager.deleteTransaction(transactionID)
        val isTransactionDeleted = true
        if (isTransactionDeleted) {
            ioController.writeWithNewLine(CLIConstants.DELETE_TRANSACTION_SUCCESS_MESSAGE)
        } else {
            ioController.writeWithNewLine(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    private fun getMonthlyReport() {
        // TODO: Refactor this after merging the code!!
//        val transactionDate = getDateInput()
//        val transactionDateTime = LocalDateTime.of(transactionDate, LocalTime.now())
//        val monthlyReport = transactionManager.getReportByMonth(transactionDateTime)
//        ioController.writeWithNewLine(monthlyReport.toString())
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
                ioController.write(CLIConstants.INVALID_OPTION_MESSAGE)
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