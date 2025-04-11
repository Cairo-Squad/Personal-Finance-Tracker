package feature.cliController

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
    const val ENTER_ID_MESSAGE = "Enter the ID >>> "
    const val ENTER_VALID_ID_MESSAGE = "Please enter a valid ID >>> "
    const val ENTER_VALID_OPTION_MESSAGE = "Please enter a valid option number >>> "
    const val SELECT_CATEGORY_MESSAGE = "Select the category you need:"
    const val ENTER_CATEGORY_NUMBER_MESSAGE = "Enter category number >>> "
    const val ENTER_VALID_CATEGORY_NUMBER_MESSAGE = "Please enter a valid category number >>> "
    const val ENTER_NAME_FOR_NEW_CATEGORY_MESSAGE = "Enter name for the new category >>> "
    const val ENTER_YES_OR_NO_MESSAGE = "Please enter \"Yes\" or \"No\" >>> "

    const val COMMON_ERROR_MESSAGE = "An error happened, please try again!"
    const val EMPTY_TRANSACTIONS_LIST_MESSAGE = "You don't have any transactions!"

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

}