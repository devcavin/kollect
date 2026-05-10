package io.github.devcavin.kollect.controller

import io.github.devcavin.kollect.dto.request.AccountStatementRequest
import io.github.devcavin.kollect.dto.request.SafaricomStaticCodeRequest
import io.github.devcavin.kollect.dto.request.SafaricomStkPushRequest
import io.github.devcavin.kollect.dto.response.AccountStatementResponse
import io.github.devcavin.kollect.dto.response.BalanceInquiryResponse
import io.github.devcavin.kollect.dto.response.SafaricomStaticCodeResponse
import io.github.devcavin.kollect.dto.response.SafaricomStkPushResponse
import io.github.devcavin.kollect.service.CreditBankService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
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
    @PostMapping("/accounts/statement")
    fun accountStatement(@Valid @RequestBody request: AccountStatementRequest): List<AccountStatementResponse> {
        return creditBankService.accountStatement(request)
    }

    // safaricom stk push
    @PostMapping("/safaricom-stkpush")
    fun safaricomStkPush(@Valid @RequestBody request: SafaricomStkPushRequest): SafaricomStkPushResponse {
        return creditBankService.safaricomStkPush(request)
    }

    // safaricom static code
    @PostMapping("/safaricom-static-code")
    fun safaricomStaticCode(@Valid @RequestBody request: SafaricomStaticCodeRequest): SafaricomStaticCodeResponse {
        return creditBankService.safaricomStaticCode(request)
    }
}