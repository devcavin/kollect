package io.github.devcavin.kollect.dto.response

import java.math.BigDecimal

data class AccountStatementResponse(
    val openingBalance: BigDecimal?,
    val accountNumber: Long?,
    val accountCurrency: String?,
    val bookingDate: String?, // example "20250505"
    val valueDate: String?, // example "20250505"
    val currency: String?,
    val amount: BigDecimal?,
    val reference: String?,
    val description: String?,
    val chequeNo: String?,
    val transactionCode: String?,
    val accountTitle1: String?,
    val accountTitle2: String?,
    val shortTitle1: String?
)
