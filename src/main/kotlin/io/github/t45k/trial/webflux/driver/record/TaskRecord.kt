package io.github.t45k.trial.webflux.driver.record

import java.time.LocalDate

class TaskRecord {

    var id: Int = 0
    lateinit var title: String
    lateinit var description: String
    lateinit var progress: String

    var dueDate: LocalDate? = null
    var completeDate: LocalDate? = null
}
