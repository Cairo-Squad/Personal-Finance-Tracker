package feature.cliController

import feature.cliController.cliDispatcher.CLIDispatcher
import feature.cliController.ioController.IOController
import test.util.Constants

// TODO: Implement!!
class CLIController(
    private val cliDispatcher: CLIDispatcher,
    private val ioController: IOController,
    private val isTestingMode: Boolean
) {

    fun start() {
        ioController.writeWithNewLine(Constants.WELCOME_MESSAGE)
        ioController.writeWithNewLine(Constants.SEPARATOR)

        while (true) {
            showMainMenu()

            val input = takeUserInput()
            if (input == Constants.EXIT_OPTION) {
                break
            }

            cliDispatcher.dispatch(input)
            ioController.writeWithNewLine(Constants.SEPARATOR)
        }

        ioController.writeWithNewLine(Constants.SEPARATOR)
        ioController.writeWithNewLine(Constants.EXIT_MESSAGE)
    }

    private fun showMainMenu() {
        ioController.writeWithNewLine(Constants.USER_MENU)
    }

    private fun takeUserInput(): Int {
        ioController.write(Constants.OPTION_INPUT_MESSAGE)
        while (true) {
            val userInput = ioController.read()
            val parsedNumber = userInput?.toIntOrNull()

            if (parsedNumber == null || parsedNumber !in 1..Constants.EXIT_OPTION) {
                ioController.write(Constants.INVALID_OPTION_MESSAGE)
            } else {
                return parsedNumber
            }
        }
    }
}