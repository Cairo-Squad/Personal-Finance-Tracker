package feature.cliController.cliDispatcher

import datasource.category.CategoryProvider
import datasource.category.ExpenseCategoryProviderImp
import datasource.category.IncomeCategoryProviderImp
import feature.cliController.ioController.IOController
import feature.transaction.TransactionManager
import model.Category
import model.Transaction
import model.TransactionType
import feature.cliController.CLIConstants
import model.convertToString
import util.category_manager.CategoryManager
import util.category_manager.CategoryState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

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

    // region Use Transaction Manager
    private fun addTransaction() {
        val transactionAmount = getAmountInput()
        val transactionDescription = getDescriptionInput()
        val transactionType = getTransactionTypeInput()
        val transactionCategory = getCategoryInput(transactionType)
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
        val transactionId = getIDInput()
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
        val newAmount = getAmountInput()
        return transactionManager.updateTransaction(Transaction(transactionAmount = newAmount))
    }

    private fun handleEditDescription(): Boolean {
        val newDescription = getDescriptionInput()
        return transactionManager.updateTransaction(Transaction(transactionDescription = newDescription))
    }

    private fun handleEditTypeAndCategory(): Boolean {
        val newType = getTransactionTypeInput()
        val newCategory = getCategoryInput(newType)
        return transactionManager.updateTransaction(
            Transaction(
                transactionType = newType,
                transactionCategory = newCategory
            )
        )
    }

    private fun handleEditDate(): Boolean {
        val newDate = getDateTimeInput()
        return transactionManager.updateTransaction(Transaction(transactionDate = newDate))
    }

    private fun deleteTransaction() {
        val transactionID = getIDInput()
        val isTransactionDeleted = transactionManager.deleteTransaction(transactionID)
        if (isTransactionDeleted) {
            ioController.writeWithNewLine(CLIConstants.DELETE_TRANSACTION_SUCCESS_MESSAGE)
        } else {
            ioController.writeWithNewLine(CLIConstants.NO_TRANSACTION_WITH_THIS_ID)
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

    private fun getCategoryInput(transactionType: TransactionType): Category {
        ioController.writeWithNewLine(CLIConstants.SELECT_CATEGORY_MESSAGE)

        val categoriesProvider = getCategoriesProvider(transactionType)
        val availableCategories = categoriesProvider.getCategories()
        ioController.writeWithNewLine(availableCategories.convertToString())

        val selectedCategory = getSelectedCategory(availableCategories)

        return if (selectedCategory.categoryName != "Other") {
            return selectedCategory
        } else {
            handleAddingNewCategory(availableCategories, categoriesProvider)
        }
    }

    private fun getCategoriesProvider(transactionType: TransactionType): CategoryProvider {
        return when (transactionType) {
            TransactionType.INCOME -> IncomeCategoryProviderImp
            TransactionType.EXPENSE -> ExpenseCategoryProviderImp
        }
    }

    private fun getSelectedCategory(availableCategories: List<Category>): Category {
        ioController.write(CLIConstants.ENTER_CATEGORY_NUMBER_MESSAGE)

        while (true) {
            val userInput = ioController.read() ?: ""
            val selectedCategory = CategoryManager.validateCategorySelection(availableCategories, userInput)

            if (selectedCategory == null) {
                ioController.write(CLIConstants.ENTER_VALID_CATEGORY_NUMBER_MESSAGE)
            } else {
                return selectedCategory
            }
        }
    }

    private fun handleAddingNewCategory(
        availableCategories: List<Category>,
        categoriesProvider: CategoryProvider
    ): Category {
        // TODO: Refactor it more, if you can!!
        ioController.write(CLIConstants.ENTER_NAME_FOR_NEW_CATEGORY_MESSAGE)
        while (true) {
            val newCategoryName = ioController.read() ?: ""
            val validationState = CategoryManager.validateNewCategoryName(availableCategories, newCategoryName)
            println(validationState.toString())

            when (validationState) {
                CategoryState.Empty -> continue
                is CategoryState.FullMatchExists -> return validationState.existCategory
                CategoryState.NewCategory -> return categoriesProvider.addCategory(newCategoryName)
                is CategoryState.PartialMatchExists -> {
                    val matchedCategory = validationState.existCategory
                    ioController.write("This name matches ${matchedCategory.categoryName}, do you want to use it? [Yes / No] >>> ")
                    when (ioController.read()?.lowercase()) {
                        "yes" -> return matchedCategory
                        "no" -> return categoriesProvider.addCategory(newCategoryName)
                        else -> ioController.write(CLIConstants.ENTER_YES_OR_NO_MESSAGE)
                    }
                }
            }
        }
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