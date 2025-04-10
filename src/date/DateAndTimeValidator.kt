package date

fun isYearValid(year:Int): Boolean {
    println(year > 2030 || year < 2025)
    return year in 2025..2030
}