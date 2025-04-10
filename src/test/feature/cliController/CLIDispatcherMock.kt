package test.feature.cliController

import feature.cliController.cliDispatcher.CLIDispatcher
import feature.cliController.ioController.IOController

class CLIDispatcherMock(private val ioController: IOController) : CLIDispatcher {

    override fun dispatch(command: Int) {
        ioController.write("Done")
    }
}