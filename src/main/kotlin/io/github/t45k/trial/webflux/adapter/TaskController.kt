package io.github.t45k.trial.webflux.adapter

import io.github.t45k.trial.webflux.adapter.response.TaskResponse
import io.github.t45k.trial.webflux.core.TaskRepository
import io.github.t45k.trial.webflux.core.entity.Task
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@RestController
class TaskController(
    private val taskRepository: TaskRepository,
) {

    @GetMapping("task")
    fun getTasks(): Flux<Task> = TODO()

    @GetMapping("task/{id}")
    fun getTask(@PathVariable id: Int): Mono<TaskResponse> =
        taskRepository.findById(id)
            .map(TaskResponse::from)
            .switchIfEmpty { throw NotFoundException() }

    @ExceptionHandler(NotFoundException::class)
    fun returnNotFoundResponse(): ResponseEntity<Nothing> = ResponseEntity.notFound().build()
}

class NotFoundException : RuntimeException()
