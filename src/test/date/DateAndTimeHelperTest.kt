package test.date

import date.getLocalDate
import date.isYearValid
import java.time.LocalDate

fun main() {
    // region year tests
    testYear(
        name = "Given date less than 2020, When date validating, Then should return false",
        result = isYearValid(2000),
        correctResult = false
    )

    testYear(
        name = "Given date more than 2030, When date validating, Then should return false",
        result = isYearValid(2033),
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
        result = getLocalDate(12,1, 2020)
    )

    testLocalDate(
        name = "Given null date, When date validating, Then should return null",
        result = getLocalDate(0, 0, 0)
    )

    testLocalDate(
        name = "Given invalid year in the date , When date validating, Then should return null",
        result = getLocalDate(12,1, 2019),
    )

}

fun testYear(name:String, result:Boolean, correctResult:Boolean) {
    if (result == correctResult) {
        println("Success - $name")
    }else{
        println("Fail - $name")
    }
}

fun testLocalDate(name:String, result:LocalDate?) {
    if (result is LocalDate) {
        println("Success - $name")
    }else{
        println("Fail - $name")
    }
}