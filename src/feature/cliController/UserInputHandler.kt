package feature.cliController

import datasource.category.CategoryProvider
import datasource.category.ExpenseCategoryProviderImp
import datasource.category.IncomeCategoryProviderImp
import feature.cliController.ioController.IOController
import model.Category
import model.TransactionType
import model.convertToString
import util.category_manager.CategoryManager
import util.category_manager.CategoryState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object UserInputHandler {

    fun getTransactionTypeInput(ioController: IOController): TransactionType {
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

    fun getCategoryInput(ioController: IOController, transactionType: TransactionType): Category {
        ioController.writeWithNewLine(CLIConstants.SELECT_CATEGORY_MESSAGE)

        val categoriesProvider = getCategoriesProvider(transactionType)
        val availableCategories = categoriesProvider.getCategories()
        ioController.writeWithNewLine(availableCategories.convertToString())

        val selectedCategory = getSelectedCategory(ioController, availableCategories)

        return if (selectedCategory.categoryName != "Other") {
            return selectedCategory
        } else {
            handleAddingNewCategory(ioController, availableCategories, categoriesProvider)
        }
    }

    private fun getCategoriesProvider(transactionType: TransactionType): CategoryProvider {
        return when (transactionType) {
            TransactionType.INCOME -> IncomeCategoryProviderImp
            TransactionType.EXPENSE -> ExpenseCategoryProviderImp
        }
    }

    private fun getSelectedCategory(ioController: IOController, availableCategories: List<Category>): Category {
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
        ioController: IOController,
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

    fun getAmountInput(ioController: IOController): Double {
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

    fun getDescriptionInput(ioController: IOController): String {
        ioController.write(CLIConstants.ENTER_DESCRIPTION_MESSAGE)
        return ioController.read() ?: ""
    }

    fun getDateTimeInput(ioController: IOController): LocalDateTime {
        val date: LocalDate = getDateInput(ioController)
        val time: LocalTime = getTimeInput(ioController)
        return LocalDateTime.of(date, time)
    }

    private fun getDateInput(ioController: IOController): LocalDate {
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

    private fun getTimeInput(ioController: IOController): LocalTime {
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

    fun getReportDateInput(ioController: IOController): LocalDateTime {
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

    fun getIDInput(ioController: IOController): Int {
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
}