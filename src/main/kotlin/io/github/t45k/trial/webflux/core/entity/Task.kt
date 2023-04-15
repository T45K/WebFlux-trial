package io.github.t45k.trial.webflux.core.entity

import io.github.t45k.trial.webflux.exception.DomainRuleViolationException
import java.time.LocalDate

data class Task(
    val id: Int,
    val title: Title,
    val description: Description,
    val progress: Progress,
    val dueDate: DueDate,
    val completeDate: LocalDate?,
) {

    fun isBehind(targetDate: LocalDate): Boolean =
        (progress == Progress.TODO || progress == Progress.DOING) &&
            dueDate.isOver(targetDate)
}

@JvmInline
value class Title(val value: String) {
    init {
        if (value.isBlank()) {
            throw DomainRuleViolationException(Title::class, Title::value, value, "Title should not be blank")
        }

        if (value.length > 50) {
            throw DomainRuleViolationException(
                Title::class,
                Title::value,
                value,
                "Length of title should be equal or less than 50"
            )
        }
    }
}

@JvmInline
value class Description(val value: String) {
    init {
        if (value.length > 150) {
            throw DomainRuleViolationException(
                Description::class,
                Description::value,
                value,
                "Length of description should be equal or less than 150"
            )
        }
    }
}
