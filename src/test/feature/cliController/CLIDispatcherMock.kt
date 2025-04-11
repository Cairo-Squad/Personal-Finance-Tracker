package test.feature.cliController

import feature.cliController.cliDispatcher.CLIDispatcher
import feature.cliController.ioController.IOController

class CLIDispatcherMock(private val ioController: IOController) : CLIDispatcher {

    override fun dispatch(userInput: Int) {
        ioController.write("Done")
    }

    override fun validateOption(option: Int): Boolean {
        return true
    }
}