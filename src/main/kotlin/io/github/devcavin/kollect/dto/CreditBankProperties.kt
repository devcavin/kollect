package io.github.devcavin.kollect.dto

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.credit-bank.api")
data class CreditBankProperties(
    val baseUrl: String,
    val appId: String,
    val apiKey: String
)