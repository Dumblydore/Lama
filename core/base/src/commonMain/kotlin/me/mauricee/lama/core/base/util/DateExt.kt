package me.mauricee.lama.core.base.util

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.days

fun LocalDateTime.Companion.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

fun LocalDate.Companion.now(): LocalDate {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
}

fun LocalDate.Companion.startOfYear(): LocalDate {
    return LocalDate(
        LocalDateTime.now().year,
        1,
        1
    )
}

fun LocalDate.Companion.endOfYear(): LocalDate {
    return LocalDate(
        LocalDateTime.now().year + 1,
        1,
        1
    ).minus(1, DateTimeUnit.DAY)
}

class DateIterator(val startDate: LocalDate,
                   val endDateInclusive: LocalDate,
                   val stepDays: Long): Iterator<LocalDate> {
    private var currentDate = startDate

    override fun hasNext() = currentDate <= endDateInclusive

    override fun next(): LocalDate {

        val next = currentDate

        currentDate = currentDate.plus(stepDays, DateTimeUnit.DAY)

        return next

    }
}

class DateProgression(override val start: LocalDate,
                      override val endInclusive: LocalDate,
                      val stepDays: Long = 1) :
    Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> =
        DateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = DateProgression(start, endInclusive, days)

}

operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)
