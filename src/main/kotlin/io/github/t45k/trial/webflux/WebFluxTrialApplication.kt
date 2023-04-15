package io.github.t45k.trial.webflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
class WebFluxTrialApplication

fun main(args: Array<String>) {
	runApplication<WebFluxTrialApplication>(*args)
}
