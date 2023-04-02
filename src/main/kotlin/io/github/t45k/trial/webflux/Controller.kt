package io.github.t45k.trial.webflux

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class Controller {

    @GetMapping("hello")
    fun hello(): Mono<Map<String, String>> = Mono.just(mapOf("hello" to "world"))

    @GetMapping("hello2")
    fun hello2(): Mono<*> = Mono.just(mapOf("hello2" to "world2"))
        .zipWhen { hello() }
        .map { it.t1 + it.t2 }
        .doOnEach { println(it) }
}
