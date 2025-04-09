package date

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateAndTimeValidator {

    fun isDateValid(
        date: String,
        pattern: String = "dd-MM-yyyy",
        minYear: Int = 2020,
        maxYear: Int = 2030
    ): Boolean {
        return try {
            val parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern))
            parsedDate.year in minYear..maxYear

        } catch (e: DateTimeParseException) {
            false
        }
    }

    fun isTimeValid(
        time: String,
        pattern: String = "HH:mm",
    ): Boolean {
        return try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern))
            true
        } catch (e: DateTimeParseException) {
            false
        }
    }

}