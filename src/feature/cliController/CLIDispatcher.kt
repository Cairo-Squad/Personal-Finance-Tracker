package feature.cliController

import feature.cliController.ioController.IOController
import feature.transaction.TransactionManager

class CLIDispatcher(
    private val transactionManager: TransactionManager,
    ioController: IOController
) {

    private val commands = emptyMap<Int, () -> Unit>()

    fun dispatch(command: Int) {

    }

    private fun addTransaction() {

    }

    private fun viewTransaction() {

    }

    private fun editTransaction() {

    }

    private fun deleteTransaction() {

    }

    private fun getSummary() {

    }
}