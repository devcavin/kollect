package io.github.devcavin.kollect.dto.request

import jakarta.validation.constraints.NotNull

data class SafaricomStaticCodeRequest(
    @field:NotNull(message = "Reference number is required")
    val RefNo: String,

    @field:NotNull(message = "Amount is required")
    val Amount: String,

    @field:NotNull(message = "Merchant name is required")
    val MerchantName: String,

    @field:NotNull(message = "CPI is required")
    val CPI: String
)
