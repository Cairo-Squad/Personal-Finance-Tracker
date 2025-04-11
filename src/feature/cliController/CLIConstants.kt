package feature.cliController

import DATE_FORMAT
import DATE_FORMAT_EXAMPLE
import MAX_YEAR
import MIN_YEAR
import MONTH_DATE_FORMAT
import MONTH_DATE_FORMAT_EXAMPLE
import SYSTEM_12_Hours
import TIME_FORMAT_EXAMPLE

object CLIConstants {

    // region User Messages
    const val WELCOME_MESSAGE = "👋🏻 Welcome to your Personal Finance Tracker!"
    const val EXIT_MESSAGE = "Thanks for your time, see you again!"

    const val SEPARATOR = "------------------------------------------------------------"

    const val OPTION_INPUT_MESSAGE = "Please enter the number of the option you need >>> "
    const val TRANSACTION_TYPE_INPUT_MESSAGE = "Please choose your transaction type:"
    const val ENTER_TRANSACTION_TYPE_INPUT_MESSAGE = "Enter transaction type number >>> "
    const val ENTER_AMOUNT_MESSAGE = "Enter the amount >>> "
    const val ENTER_VALID_AMOUNT_MESSAGE = "Please enter a valid amount >>> "
    const val ENTER_DESCRIPTION_MESSAGE = "Enter the description >>> "
    const val ENTER_ID_MESSAGE = "Enter transaction ID >>> "
    const val ENTER_VALID_ID_MESSAGE = "Please enter a valid ID >>> "
    const val ENTER_VALID_OPTION_MESSAGE = "Please enter a valid option number >>> "
    const val SELECT_CATEGORY_MESSAGE = "Select the category you need:"
    const val ENTER_CATEGORY_NUMBER_MESSAGE = "Enter category number >>> "
    const val ENTER_VALID_CATEGORY_NUMBER_MESSAGE = "Please enter a valid category number >>> "
    const val ENTER_NAME_FOR_NEW_CATEGORY_MESSAGE = "Enter name for the new category >>> "
    const val ENTER_YES_OR_NO_MESSAGE = "Please enter \"Yes\" or \"No\" >>> "
    const val TRANSACTION_DETAILS_MESSAGE = "Transaction details:"
    const val ALL_TRANSACTIONS_MESSAGE = "All Transactions:"
    const val ENTER_EDIT_CHOICE_MESSAGE = "Enter your choice >>> "
    const val ENTER_DATE_MESSAGE =
        "Enter the date using format ($DATE_FORMAT) between $MIN_YEAR and $MAX_YEAR, e.g. $DATE_FORMAT_EXAMPLE >>> "
    const val ENTER_VALID_DATE_MESSAGE = "Please enter a valid date >>> "
    const val ENTER_TIME_MESSAGE = "Enter the time using format ($SYSTEM_12_Hours), e.g. $TIME_FORMAT_EXAMPLE >>> "
    const val ENTER_VALID_TIME_MESSAGE = "Please enter a valid time >>> "
    const val ENTER_MONTH_DATE_MESSAGE =
        "Enter the date using format $MONTH_DATE_FORMAT, e.g. $MONTH_DATE_FORMAT_EXAMPLE >>> "
    const val ENTER_VALID_MONTH_DATE_MESSAGE = "Please enter a valid date >>> "

    const val COMMON_ERROR_MESSAGE = "An error happened, please try again!"
    const val EMPTY_TRANSACTIONS_LIST_MESSAGE = "You don't have any transactions!"
    const val NO_TRANSACTION_WITH_THIS_ID = "There is no transaction with this ID!"

    const val ADD_TRANSACTION_SUCCESS_MESSAGE = "Your transaction is added successfully."
    const val DELETE_TRANSACTION_SUCCESS_MESSAGE = "Your transaction is deleted successfully."
    const val EDIT_TRANSACTION_SUCCESS_MESSAGE = "Your transaction is edited successfully."

    val USER_MENU = """
            Available Options:-
            1. Add new transaction
            2. View all transactions details
            3. View a single transaction details
            4. Edit a transaction data
            5. Delete a transaction
            6. View monthly report
            7. Exit
            
        """.trimIndent()

    val UPDATE_OPTIONS = """
        Available Options:-
        1. Edit Amount
        2. Edit Description
        3. Edit Type & Category
        4. Edit Date
        5. Exit  
    """.trimIndent()

    // endregion

    // region Command Codes
    const val ADD_COMMAND_CODE = 1
    const val VIEW_ALL_COMMAND_CODE = 2
    const val VIEW_SINGLE_TRANSACTION_COMMAND_CODE = 3
    const val UPDATE_COMMAND_CODE = 4
    const val DELETE_COMMAND_CODE = 5
    const val MONTHLY_REPORT_COMMAND_CODE = 6
    const val EXIT_COMMAND_CODE = 7

    // endregion

    // region Edit Options Codes
    const val EDIT_AMOUNT_CODE = 1
    const val EDIT_DESCRIPTION_CODE = 2
    const val EDIT_TYPE_CATEGORY_CODE = 3
    const val EDIT_DATE_CODE = 4
    const val EXIT_EDIT_CODE = 5

    // endregion

}