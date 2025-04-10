package date

fun isYearValid(year:Int): Boolean {
    return year in MIN_YEAR..MAX_YEAR
}