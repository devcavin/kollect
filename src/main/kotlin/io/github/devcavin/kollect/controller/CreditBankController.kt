package io.github.devcavin.kollect.controller

import io.github.devcavin.kollect.dto.request.AccountStatementRequest
import io.github.devcavin.kollect.dto.response.AccountStatementResponse
import io.github.devcavin.kollect.dto.response.BalanceInquiryResponse
import io.github.devcavin.kollect.service.CreditBankService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class BalanceController(
    private val creditBankService: CreditBankService
) {
    // balance inquiry
    @GetMapping("accounts/balance" )
    fun balanceInquiry(): BalanceInquiryResponse {
        return creditBankService.balanceInquiry()
    }

    // account statement
    @GetMapping("/accounts/statement")
    fun accountStatement(@Valid @RequestBody request: AccountStatementRequest): AccountStatementResponse {
        return creditBankService.accountStatement(request)
    }
}