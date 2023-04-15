package io.github.t45k.trial.webflux.core

import io.github.t45k.trial.webflux.core.entity.Task
import reactor.core.publisher.Mono

interface TaskRepository {

    fun findById(id: Int): Mono<Task>
}
