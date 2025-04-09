package date

data class DateAndTime(
    private var date: String,
    private var time: String
) {
    fun setDate(year: Int, month: Int, day: Int) {
        date = "$day-$month-$year"
    }

    fun setTime(hour: Int, minute: Int) {
        time = "$hour:$minute"
    }

    fun getDate(): String {
        return date
    }

    fun getTime(): String {
        return time
    }
}