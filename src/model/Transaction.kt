package model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Transaction(
    val transactionId: Int? = null,
    val transactionDescription: String? = null,
    val transactionType: TransactionType? = null,
    val transactionAmount: Double? = null,
    val transactionDate: LocalDateTime? = null,
    val transactionCategory: Category? = null
) {
    override fun toString(): String {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' hh:mm a")

        return """
            ID: $transactionId
            Description: $transactionDescription
            Type: ${transactionType?.value}
            Amount: $transactionAmount
            Date: ${transactionDate?.format(dateTimeFormatter)}
            Category: ${transactionCategory?.categoryName}
        """.trimIndent()
    }
}

fun Transaction.notNullValues(): List<Any> {
    return listOfNotNull(
        this.transactionAmount,
        this.transactionDescription,
        this.transactionCategory,
        this.transactionType,
        this.transactionDate,
    )
}