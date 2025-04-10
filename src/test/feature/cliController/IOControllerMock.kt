package test.feature.cliController

import feature.cliController.ioController.IOController

class IOControllerMock(private val userInputs: List<String>) : IOController {

    private val terminalOutputs = mutableListOf<String>()
    private var inputIndex = 0

    override fun read(): String? {
        return userInputs.getOrNull(inputIndex++)
    }

    override fun write(message: String) {
        terminalOutputs.add(message)
    }

    fun getOutputs(): List<String> = terminalOutputs
}