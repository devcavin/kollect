package io.github.devcavin.kollect.service

import io.github.devcavin.kollect.dto.request.AccountStatementRequest
import io.github.devcavin.kollect.dto.response.AccountStatementResponse
import io.github.devcavin.kollect.dto.response.BalanceInquiryResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class CreditBankService(
    private val restClient: RestClient
) {
    private val logger: Logger = LoggerFactory.getLogger(CreditBankService::class.java)
    // balance inquiry
    fun balanceInquiry(): BalanceInquiryResponse {
        return try {
            restClient.post()
                .uri("/balance-inquiry")
                .retrieve()
                .body<BalanceInquiryResponse>()
                ?: error("Empty response from API")
        } catch (e: Exception) {
            logger.error("Balance retrieval failed: ${e.message}")
            throw e
        }
    }

    // account statement
    fun accountStatement(request: AccountStatementRequest): AccountStatementResponse {
        return try {
            restClient.post()
                .uri("/account-statement")
                .body(request)
                .retrieve()
                .body<AccountStatementResponse>()
                ?: error("Empty response from API")

        } catch (e: Exception) {
            logger.error("Account statement retrieval failed: ${e.message}")
            throw e
        }
    }
}