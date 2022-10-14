package ohad.sa.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatterBuilder
import java.util.*
import kotlin.time.Duration.Companion.hours


fun String.getTimePast(): String {
    val formatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").toFormatter()
    val date: LocalDateTime = LocalDateTime.from(formatter.parse(this))
    val period = System.currentTimeMillis() - date.toInstant(ZoneOffset.ofHours(2)).toEpochMilli()
    return ((period) / (1000 * 60 * 60 * 24)).toInt().getTimePast()
}


fun Int.getTimePast(): String {
    val years = this / 365
    val months = (this - years * 365) / 30
    val days = (this - years * 365 - months * 30)

    if (years == 0 && months == 0 && days == 0) {
        val hours = this / 60
        val minutes = (this - hours * 60) / 60
        val seconds = (this - hours * 60 - minutes * 60)
        return (if (hours > 0) "$hours hours, " else "") +
                (if (minutes > 0) "$minutes minutes, " else "") + "ego"
    }

    return (if (years > 0) "$years years, " else "") +
            (if (months > 0) "$months months, " else "") +
            (if (days > 0) "$days days " else "") + "ego"
}