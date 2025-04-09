package test.feature.cliController

import feature.cliController.CLIController

fun main() {

}

private fun testUserMenu() {
    val ioController = IOControllerMock(listOf())
    val cliDispatcher = CLIDispatcherMock(ioController)
    val cliController = CLIController(cliDispatcher, ioController, true)
    // TODO: Complete!!
//    cliController.showMainMenu()
    println(ioController.getOutputs())
}