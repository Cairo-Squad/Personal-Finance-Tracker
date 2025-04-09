package financeReport.data

class Transaction(
    val transactionId: Int,
    val transactionDescription: String = "",
    val transactionType: TransactionType,
    val transactionAmount: Double,
    val transactionDate: Long,
    val transactionCategory: Category
) {


}
