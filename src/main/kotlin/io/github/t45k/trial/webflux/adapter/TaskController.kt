package io.github.t45k.trial.webflux.adapter

import io.github.t45k.trial.webflux.adapter.response.TaskResponse
import io.github.t45k.trial.webflux.core.TaskRepository
import io.github.t45k.trial.webflux.core.entity.Progress
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@RestController
class TaskController(
    private val taskRepository: TaskRepository,
) {

    @GetMapping("task")
    fun getTasks(
        @RequestParam(
            name = "progress",
            defaultValue = "todo,doing,done",
            required = false
        ) filteringProgress: String
    ): Flux<TaskResponse> {
        val progresses = filteringProgress.split(",")
            .map { Progress.fromValueOrNull(it.trim()) ?: throw BadRequestException() }
        return taskRepository.findAllByProgress(progresses).map(TaskResponse::from)
    }

    @GetMapping("task/{id}")
    fun getTask(@PathVariable id: Int): Mono<TaskResponse> =
        taskRepository.findById(id)
            .map(TaskResponse::from)
            .switchIfEmpty { throw NotFoundException() }

    @ExceptionHandler(NotFoundException::class)
    fun returnNotFoundResponse(): ResponseEntity<Nothing> = ResponseEntity.notFound().build()

    @ExceptionHandler(BadRequestException::class)
    fun returnBadRequestResponse(): ResponseEntity<Nothing> = ResponseEntity.badRequest().build()
}

class NotFoundException : RuntimeException()
class BadRequestException : RuntimeException()
