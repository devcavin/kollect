package io.github.devcavin.kollect

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KollectApplication

fun main(args: Array<String>) {
    runApplication<KollectApplication>(*args)
}
