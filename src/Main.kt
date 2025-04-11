import datasource.storage.MemoryStorageImp
import feature.cliController.CLIController
import feature.cliController.cliDispatcher.CLIDispatcherImpl
import feature.cliController.ioController.IOControllerImpl
import feature.transaction.TransactionManagerImpl

fun main() {
    val ioController = IOControllerImpl()
    val transactionManager = TransactionManagerImpl(MemoryStorageImp)
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    val cliController = CLIController(cliDispatcher, ioController, false)
    cliController.start()
}