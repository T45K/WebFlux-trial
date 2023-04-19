package io.github.t45k.trial.webflux.driver

import io.github.t45k.trial.webflux.core.TaskRepository
import io.github.t45k.trial.webflux.core.entity.Description
import io.github.t45k.trial.webflux.core.entity.DueDate
import io.github.t45k.trial.webflux.core.entity.Progress
import io.github.t45k.trial.webflux.core.entity.Task
import io.github.t45k.trial.webflux.core.entity.Title
import io.github.t45k.trial.webflux.driver.dao.TaskDao
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {

    override fun findById(id: Int): Mono<Task> =
        taskDao.selectById(id).map {
            Task(
                id,
                Title(it.title),
                Description(it.description),
                Progress.fromValueOrThrow(it.progress),
                if (it.dueDate != null) DueDate.FixedDate(it.dueDate!!) else DueDate.Unlimited,
                it.completeDate
            )
        }

    override fun findAllByProgress(progresses: List<Progress>): Flux<Task> =
        taskDao.selectAllByProgresses(progresses.map { it.value }).map {
            Task(
                it.id,
                Title(it.title),
                Description(it.description),
                Progress.fromValueOrThrow(it.progress),
                if (it.dueDate != null) DueDate.FixedDate(it.dueDate!!) else DueDate.Unlimited,
                it.completeDate
            )
        }
}
