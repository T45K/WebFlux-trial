package io.github.t45k.trial.webflux.core.entity

import java.time.LocalDate

sealed interface DueDate {

    fun isOver(targetDate: LocalDate): Boolean

    @JvmInline
    value class FixedDate(val value: LocalDate) : DueDate {
        override fun isOver(targetDate: LocalDate): Boolean = targetDate >= value
    }

    object Unlimited : DueDate {
        override fun isOver(targetDate: LocalDate): Boolean = false
    }
}
