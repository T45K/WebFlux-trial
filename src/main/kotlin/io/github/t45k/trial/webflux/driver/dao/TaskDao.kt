package io.github.t45k.trial.webflux.driver.dao

import io.github.t45k.trial.webflux.driver.record.TaskRecord
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface TaskDao : ReactiveCrudRepository<TaskRecord, Int> {

    @Query(
        """
        select * from task
        where id = :id
    """
    )
    fun selectById(id: Int): Mono<TaskRecord>

    @Query(
        """
        select * from task
        where progress in (:progresses)
    """
    )
    fun selectAllByProgresses(progresses: List<String>): Flux<TaskRecord>
}
