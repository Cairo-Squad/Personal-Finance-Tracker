package util.date

import MAX_YEAR
import MIN_YEAR

fun isYearValid(year:Int): Boolean {
    return year in MIN_YEAR..MAX_YEAR
}