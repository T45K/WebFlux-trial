package io.github.t45k.trial.webflux.core.entity

import io.github.t45k.trial.webflux.exception.DomainRuleViolationException

enum class Progress(val value: String) {
    TODO("todo"), DOING("doing"), DONE("done");

    companion object {
        fun fromValueOrNull(value: String): Progress? = Progress.values().find { it.value == value }

        fun fromValueOrThrow(value: String): Progress = fromValueOrNull(value)
            ?: throw DomainRuleViolationException(
                Progress::class,
                Progress::value,
                value,
                "value of Progress should be todo, doing, or done"
            )
    }
}
