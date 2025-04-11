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

    const val INVALID_OPTION_MESSAGE = "Please enter a valid option number >>> "
    const val COMMON_ERROR_MESSAGE = "An error happened, please try again."

    const val ADD_TRANSACTION_SUCCESS_MESSAGE = "Your transaction is added successfully"
    const val DELETE_TRANSACTION_SUCCESS_MESSAGE = "Your transaction is deleted successfully"
    const val EDIT_TRANSACTION_SUCCESS_MESSAGE = "Your transaction is edited successfully"

    val USER_MENU = """
            Available Options:-
            1. Add new transaction
            2. View a transaction details
            3. Edit a transaction data
            4. Delete a transaction
            5. View monthly report
            6. Exit
            
        """.trimIndent()

    // endregion

    // region Command Codes
    const val ADD_COMMAND_CODE = 1
    const val VIEW_COMMAND_CODE = 2
    const val UPDATE_COMMAND_CODE = 3
    const val DELETE_COMMAND_CODE = 4
    const val MONTHLY_REPORT_COMMAND_CODE = 5
    const val EXIT_OPTION = 6

    // endregion

}