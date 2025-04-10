package date

import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


const val AM = "AM"
const val PM = "PM"
const val FORMAT = "%02d:%02d %s"
const val SYSTEM_12_Hours = "hh:mm a"
const val SYSTEM_24_Hours = "HH:mm"

fun showTime12Hours(time: LocalTime?): String? {
    val displayFormatter = DateTimeFormatter.ofPattern(SYSTEM_12_Hours)
    return time?.format(displayFormatter)

}

fun showTime24Hours(time: LocalTime?): String? {
    val displayFormatter = DateTimeFormatter.ofPattern(SYSTEM_24_Hours)
    return time?.format(displayFormatter)

}

fun getLocalDateTime(year: Int, month: Int, day: Int, hour: Int, minute: Int): LocalDateTime? {
    return LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute))
}

fun getLocalDate(day: Int, month: Int, year: Int): LocalDate? {
    return if (isYearValid(year))
        try {
            LocalDate.of(year, month, day)
        } catch (e: Exception) {
            return null
        }
    else
        null
}

fun getLocalTime(hour: Int, minute: Int, period: String = AM): LocalTime? {
    return try {
        val timeString = String.format(FORMAT, hour, minute, period)
        LocalTime.parse(timeString, DateTimeFormatter.ofPattern("hh:mm a"))
    } catch (e: Exception) {
        return null
    }
}


