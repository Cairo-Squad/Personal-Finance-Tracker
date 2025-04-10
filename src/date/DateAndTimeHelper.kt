package date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun getLocalDateTime(year: Int, month: Int, day: Int, hour: Int, minute: Int): LocalDateTime {
    return LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute))
}

fun getLocalDate(day: Int, month: Int, year: Int): LocalDate? {
    return if (isYearValid(year))
        LocalDate.of(year, month, day)
    else
        null
}