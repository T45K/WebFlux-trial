package io.github.t45k.trial.webflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebFluxTrialApplication

fun main(args: Array<String>) {
	runApplication<WebFluxTrialApplication>(*args)
}
