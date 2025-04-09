package model

import java.time.LocalDate

data class Transaction(
    val transactionId: Int,
    val transactionDescription: String = "",
    val transactionType: TransactionType,
    val transactionAmount: Double,
    val transactionDate: LocalDate,
    val transactionCategory: Category
)