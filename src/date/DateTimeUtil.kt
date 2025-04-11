package date

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


const val AM = "AM"
const val PM = "PM"
const val TIME_FORMAT = "%02d:%02d %s"
const val DATE_FORMAT = "dd-MM-yyyy"
const val SYSTEM_12_Hours = "hh:mm a"
const val SYSTEM_24_Hours = "HH:mm"
const val MAX_YEAR = 2030
const val MIN_YEAR = 1900


fun getLocalDate(date: String): LocalDate? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        val date = LocalDate.parse(date, formatter)
        if (isYearValid(date.year)) date else null
    } catch (e: Exception) {
        null
    }
}

fun getLocalTime(time:String): LocalTime? {
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
