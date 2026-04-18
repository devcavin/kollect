package io.github.devcavin.kollect.dto.response

import java.math.BigDecimal

data class BalanceInquiryResponse(
    val responseCode: String?,
    val dateAndTimeTransmission: String?,
    val countryCode: String?,
    val actualBalance: BigDecimal?,
    val availableBalance: BigDecimal?
)