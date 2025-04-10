package test.util

object Constants {

    // region User Messages
    const val WELCOME_MESSAGE = "👋🏻 Welcome to your Personal Finance Tracker!"
    const val EXIT_MESSAGE = "Thanks for your time, see you again!"

    const val SEPARATOR = "------------------------------------------------------------"

    const val OPTION_INPUT_MESSAGE = "Please enter the number of the option you need >>> "

    const val INVALID_OPTION_MESSAGE = "Please enter a valid option number >>> "
    const val INVALID_COMMAND_MESSAGE = "An error happened, please try again."



    val USER_MENU = """
            1. Add new transaction
            2. View a transaction details
            3. Edit a transaction data
            4. Delete a transaction
            5. View monthly report
            6. Exit
        """.trimIndent()

    // endregion

    const val EXIT_OPTION = 6

}