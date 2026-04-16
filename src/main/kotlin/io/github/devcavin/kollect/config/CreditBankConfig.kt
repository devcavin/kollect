package io.github.devcavin.kollect.config

import io.github.devcavin.kollect.dto.CreditBankProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class CreditBankConfig {

    @Bean
    fun creditBankClient(properties: CreditBankProperties): RestClient {
        return RestClient
            .builder()
            .baseUrl(properties.baseUrl)
            .defaultHeader("x-app-id", properties.appId)
            .defaultHeader("x-api-key", properties.apiKey)
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Accept", "application/json")
            .build()
    }
}