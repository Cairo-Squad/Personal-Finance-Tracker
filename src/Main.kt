import datasource.storage.MemoryStorageImp
import feature.cliController.CLIController
import feature.cliController.cliDispatcher.CLIDispatcherImpl
import feature.cliController.ioController.IOControllerImpl
import feature.financeReport.MonthlyFinanceReporterImpl
import feature.transaction.TransactionManagerImpl

fun main() {
    val ioController = IOControllerImpl()
    val monthlyReporter = MonthlyFinanceReporterImpl()
    val transactionManager = TransactionManagerImpl(MemoryStorageImp, monthlyReporter)
    val cliDispatcher = CLIDispatcherImpl(transactionManager, ioController)
    val cliController = CLIController(cliDispatcher, ioController, false)
    cliController.start()
}