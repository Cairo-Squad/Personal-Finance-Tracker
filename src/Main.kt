import datasource.MemoryStorage
import feature.cliController.CLIController
import feature.cliController.cliDispatcher.CLIDispatcherImpl
import feature.cliController.ioController.IOControllerImpl
import feature.transaction.TransactionManagerImpl

fun main() {
    val ioController = IOControllerImpl()
    val storageManager = MemoryStorage()
    val transactionManager = TransactionManagerImpl(storageManager)
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    val cliController = CLIController(cliDispatcher, ioController, false)
    cliController.start()
}