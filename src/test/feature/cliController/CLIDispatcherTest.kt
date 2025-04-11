package test.feature.cliController

import feature.cliController.cliDispatcher.CLIDispatcherImpl
import feature.transaction.TransactionManager

fun main() {
    val transactionManager = TransactionManagerMock()
    testAddTransactionOption(transactionManager)
    testEditTransactionOption(transactionManager)
    testDeleteTransactionOption(transactionManager)
    testViewTransactionOption(transactionManager)
    testGetSummaryOption(transactionManager)
    testInvalidOption(transactionManager)
}

private fun testAddTransactionOption(transactionManager: TransactionManager) {
    val ioController = IOControllerMock(listOf())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(1)
    println(ioController.getOutputs())
}

private fun testEditTransactionOption(transactionManager: TransactionManager) {
    val ioController = IOControllerMock(listOf())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(2)
    println(ioController.getOutputs())
}

private fun testDeleteTransactionOption(transactionManager: TransactionManager) {
    val ioController = IOControllerMock(listOf())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(3)
    println(ioController.getOutputs())
}

private fun testViewTransactionOption(transactionManager: TransactionManager) {
    val ioController = IOControllerMock(listOf())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(4)
    println(ioController.getOutputs())
}

private fun testGetSummaryOption(transactionManager: TransactionManager) {
    val ioController = IOControllerMock(listOf())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(5)
    println(ioController.getOutputs())
}

private fun testInvalidOption(transactionManager: TransactionManager) {
    val ioController = IOControllerMock(listOf())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(100)
    println(ioController.getOutputs())
}