package io.github.t45k.trial.webflux.adapter.response

import io.github.t45k.trial.webflux.core.entity.DueDate
import io.github.t45k.trial.webflux.core.entity.Task

data class TaskResponse(
    val id: Int,
    val title: String,
    val description: String,
    val progress: String,
    val dueDate: String?,
    val completeDate: String?,
) {

    companion object {
        fun from(task: Task): TaskResponse =
            TaskResponse(
                task.id,
                task.title.value,
                task.description.value,
                task.progress.value,
                if (task.dueDate is DueDate.FixedDate) task.dueDate.value.toString() else null,
                task.completeDate?.toString()
            )
    }
}
