package util.date

import DATE_FORMAT
import SYSTEM_12_Hours
import SYSTEM_24_Hours
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


fun main() {
    val date = getLocalDate("12-1-2020")
    val time = getLocalTime("12:10")
    val dateTime = getLocalDateTime(date, time)
    println(dateTime?.month)

}
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

fun getLocalTime(time: String): LocalTime? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(SYSTEM_24_Hours)
        LocalTime.parse(time, formatter)
    } catch (e: Exception) {
        return null
    }
}

fun showTime12Hours(time: LocalTime?): String? {
    val displayFormatter = DateTimeFormatter.ofPattern(SYSTEM_12_Hours)
    return time?.format(displayFormatter)

}

fun showTime24Hours(time: LocalTime?): String? {
    val displayFormatter = DateTimeFormatter.ofPattern(SYSTEM_24_Hours)
    return time?.format(displayFormatter)
}
