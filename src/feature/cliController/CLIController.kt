package feature.cliController

import feature.cliController.cliDispatcher.CLIDispatcher
import feature.cliController.ioController.IOController

class CLIController(
    private val cliDispatcher: CLIDispatcher,
    private val ioController: IOController,
    private val isTestingMode: Boolean
) {

    fun start() {
        ioController.writeWithNewLine(CLIConstants.WELCOME_MESSAGE)
        ioController.writeWithNewLine(CLIConstants.SEPARATOR)

        while (true) {
            showMainMenu()

            val input = takeUserInput()
            if (input == CLIConstants.EXIT_OPTION) {
                break
            }

            cliDispatcher.dispatch(input)
            ioController.writeWithNewLine(CLIConstants.SEPARATOR)
        }

        ioController.writeWithNewLine(CLIConstants.SEPARATOR)
        ioController.writeWithNewLine(CLIConstants.EXIT_MESSAGE)
    }

    private fun showMainMenu() {
        ioController.writeWithNewLine(CLIConstants.USER_MENU)
    }

    private fun takeUserInput(): Int {
        ioController.write(CLIConstants.OPTION_INPUT_MESSAGE)
        while (true) {
            val userInput = ioController.read()
            val parsedNumber = userInput?.toIntOrNull()

            if (parsedNumber == null || parsedNumber !in 1..CLIConstants.EXIT_OPTION) {
                ioController.write(CLIConstants.INVALID_OPTION_MESSAGE)
            } else {
                return parsedNumber
            }
        }
    }
}