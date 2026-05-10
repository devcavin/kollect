package io.github.devcavin.kollect.service

import io.github.devcavin.kollect.dto.request.AccountStatementRequest
import io.github.devcavin.kollect.dto.request.SafaricomStaticCodeRequest
import io.github.devcavin.kollect.dto.request.SafaricomStkPushRequest
import io.github.devcavin.kollect.dto.response.AccountStatementResponse
import io.github.devcavin.kollect.dto.response.BalanceInquiryResponse
import io.github.devcavin.kollect.dto.response.SafaricomStaticCodeResponse
import io.github.devcavin.kollect.dto.response.SafaricomStkPushResponse
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
    fun accountStatement(request: AccountStatementRequest): List<AccountStatementResponse> {
        return try {
            restClient.post()
                .uri("/account-statement")
                .body(request)
                .retrieve()
                .body<List<AccountStatementResponse>>()
                ?: error("Empty response from API")

        } catch (e: Exception) {
            logger.error("Account statement retrieval failed: ${e.message}")
            throw e
        }
    }

    // safaricom stk push
    fun safaricomStkPush(request: SafaricomStkPushRequest): SafaricomStkPushResponse {
        return try {
            restClient.post()
                .uri("/safaricom-stkpush")
                .body(request)
                .retrieve()
                .body<SafaricomStkPushResponse>()
            ?: error("Empty response from API")
        } catch (e: Exception) {
            logger.error("Safaricom STK push failed: ${e.message}")
            throw e
        }
    }

    // safaricom static code
    fun safaricomStaticCode(request: SafaricomStaticCodeRequest): SafaricomStaticCodeResponse {
        return try {
            restClient.post()
                .uri("/safaricom-static-code")
                .body(request)
                .retrieve()
                .body<SafaricomStaticCodeResponse>()
            ?: error("Empty response from API")
        } catch (e: Exception) {
            logger.error("Safaricom static code failed: ${e.message}")
            throw e
        }
    }
}