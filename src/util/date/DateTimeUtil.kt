package util.date

import DATE_FORMAT
import TIME_24H_FORMAT
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

fun getLocalDateTime(date: LocalDate?, time: LocalTime?): LocalDateTime? {
    return if (date != null && time != null) {
        LocalDateTime.of(date, time)
    } else {
        null
    }
}
fun getLocalDate(date: String): LocalDate? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        val date = LocalDate.parse(date, formatter)
        if (isYearValid(date.year)) date else null
    } catch (e: Exception) {
        null
    }
}

fun getLocalTime(time: String, formatter: String = TIME_24H_FORMAT): LocalTime? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH)
        LocalTime.parse(time, formatter)
    } catch (e: Exception) {
        return null
    }
}

fun showTimeSystem(time: LocalTime?, formatter: String = TIME_24H_FORMAT): String? {
    val displayFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH)
    return time?.format(displayFormatter)
}