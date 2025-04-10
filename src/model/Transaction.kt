package model

import java.time.LocalDateTime

data class Transaction(
    val transactionId: Int?,
    val transactionDescription: String?,
    val transactionType: TransactionType?,
    val transactionAmount: Double?,
    val transactionDate: LocalDateTime?,
    val transactionCategory: Category?
)