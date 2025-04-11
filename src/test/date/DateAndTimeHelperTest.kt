package test.date

import util.date.getLocalDate
import util.date.getLocalDateTime
import util.date.getLocalTime
import util.date.isYearValid
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun main() {
    // region year tests
    testYear(
        name = "Given date less than 1900, When date validating, Then should return false",
        result = isYearValid(1100),
        correctResult = false
    )

    testYear(
        name = "Given date more than 2030, When date validating, Then should return false",
        result = isYearValid(2133),
        correctResult = false
    )

    testYear(
        name = "Given date between 2020 to 2030, When date validating, Then should return true",
        result = isYearValid(2023),
        correctResult = true
    )
    // endregion

    // region test local date

    testLocalDate(
        name = "Given valid date, When date validating, Then should return LocalDate object",
        result = getLocalDate("12-01-2020"),
        correctResult = true
    )

    testLocalDate(
        name = "Given invalid date by month 1 instead of 01, When date validating, Then should return LocalDate object",
        result = getLocalDate("12-1-2020"),
        correctResult = false
    )

    testLocalDate(
        name = "Given zeros date, When date validating, Then should return null",
        result = getLocalDate("0-0-0"),
        correctResult = false
    )

    testLocalDate(
        name = "Given invalid year in the date , When date validating, Then should return null",
        result = getLocalDate("12-1-1888"),
        correctResult = false
    )
    // endregion


    // region test local time

    testLocalTime(
        name = "Given valid time, When time validating, Then should return LocalDate object",
        result = getLocalTime("12:10"),
        correctResult = true
    )

    testLocalTime(
        name = "Given empty time, When time validating, Then should return null",
        result = getLocalTime(""),
        correctResult = false
    )

    testLocalTime(
        name = "Given invalid time over 23, When time validating, Then should return null",
        result = getLocalTime("25:11"),
        correctResult = false
    )
    // endregion

    // region test local dateTime

    testLocalDateTime(
        name = "Given valid date and time, When dateTime validating, Then should return LocalDateTime object",
        result = getLocalDateTime(getLocalDate("12-11-2023"), getLocalTime("14:20")),
        correctResult = true
    )
    testLocalDateTime(
        name = "Given invalid date and time, When dateTime validating, Then should return null",
        result = getLocalDateTime(getLocalDate("12-11-11"), getLocalTime("14:20")),
        correctResult = false
    )
    testLocalDateTime(
        name = "Given empty date and time, When dateTime validating, Then should return null",
        result = getLocalDateTime(getLocalDate(""), getLocalTime("")),
        correctResult = false
    )

    // endregion
}

fun testYear(name:String, result:Boolean, correctResult:Boolean) {
    if (result == correctResult) {
        println("Success - $name")
    }else{
        println("Fail - $name")
    }
}

fun testLocalDate(
    name: String,
    result: LocalDate?,
    correctResult: Boolean
) {
    if ((result == null && !correctResult) || (result != null && correctResult)) {
        println("Success - $name")
    } else {
        println("Fail - $name")
    }
}

fun testLocalTime(
    name: String,
    result: LocalTime?,
    correctResult: Boolean
) {
    if ((result == null && !correctResult) || (result != null && correctResult)) {
        println("Success - $name")
    } else {
        println("Fail - $name")
    }
}

fun testLocalDateTime(
    name: String,
    result: LocalDateTime?,
    correctResult: Boolean
) {
    if ((result == null && !correctResult) || (result != null && correctResult)) {
        println("Success - $name")
    } else {
        println("Fail - $name")
    }
}