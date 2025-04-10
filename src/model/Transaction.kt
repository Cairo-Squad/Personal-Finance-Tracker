package model

import java.time.LocalDateTime

data class Transaction(
    val transactionId: Int? = null,
    val transactionDescription: String? = null,
    val transactionType: TransactionType? = null,
    val transactionAmount: Double? = null,
    val transactionDate: LocalDateTime? = null,
    val transactionCategory: Category? = null
)