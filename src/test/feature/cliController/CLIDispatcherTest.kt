package test.feature.cliController

import datasource.MemoryStorage
import feature.cliController.cliDispatcher.CLIDispatcherImpl
import feature.transaction.TransactionManagerImpl

fun main() {
    testAddTransactionOption()
    testEditTransactionOption()
    testDeleteTransactionOption()
    testViewTransactionOption()
    testGetSummaryOption()
    testInvalidOption()
}

private fun testAddTransactionOption() {
    val ioController = IOControllerMock(listOf())
    // TODO: Create a mock!!!
    val transactionManager = TransactionManagerImpl(MemoryStorage())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(1)
    println(ioController.getOutputs())
}

private fun testEditTransactionOption() {
    val ioController = IOControllerMock(listOf())
    // TODO: Create a mock!!!
    val transactionManager = TransactionManagerImpl(MemoryStorage())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(2)
    println(ioController.getOutputs())
}

private fun testDeleteTransactionOption() {
    val ioController = IOControllerMock(listOf())
    // TODO: Create a mock!!!
    val transactionManager = TransactionManagerImpl(MemoryStorage())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(3)
    println(ioController.getOutputs())
}

private fun testViewTransactionOption() {
    val ioController = IOControllerMock(listOf())
    // TODO: Create a mock!!!
    val transactionManager = TransactionManagerImpl(MemoryStorage())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(4)
    println(ioController.getOutputs())
}

private fun testGetSummaryOption() {
    val ioController = IOControllerMock(listOf())
    // TODO: Create a mock!!!
    val transactionManager = TransactionManagerImpl(MemoryStorage())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(5)
    println(ioController.getOutputs())
}

private fun testInvalidOption() {
    val ioController = IOControllerMock(listOf())
    // TODO: Create a mock!!!
    val transactionManager = TransactionManagerImpl(MemoryStorage())
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    cliDispatcher.dispatch(100)
    println(ioController.getOutputs())
}