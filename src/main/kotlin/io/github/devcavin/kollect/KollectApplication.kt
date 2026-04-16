package io.github.devcavin.kollect

import io.github.devcavin.kollect.dto.CreditBankProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(CreditBankProperties::class)
class KollectApplication

fun main(args: Array<String>) {
    runApplication<KollectApplication>(*args)
}
