package feature.cliController.cliDispatcher

import feature.cliController.ioController.IOController
import feature.transaction.TransactionManagerImpl

// TODO: Implement!!
class CLIDispatcherImpl(
    private val transactionManager: TransactionManagerImpl,
    private val ioController: IOController
) : CLIDispatcher {

    private val commands = emptyMap<Int, () -> Unit>()

    override fun dispatch(command: Int) {

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