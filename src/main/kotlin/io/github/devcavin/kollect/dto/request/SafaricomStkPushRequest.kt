package io.github.devcavin.kollect.dto.request

import jakarta.validation.constraints.NotNull

data class SafaricomStkPushRequest(
    @field:NotNull(message = "Phone number is required")
    val phoneNumber: String,

    @field:NotNull(message = "Amount is required")
    val amount: Double,

    @field:NotNull(message = "Reference is required")
    val reference: String,

    @field:NotNull(message = "Country code is required")
    val countryCode: String,

    @field:NotNull(message = "Narration is required")
    val narration: String,

    @field:NotNull(message = "Callback URL is required")
    val callBackUrl: String,

    @field:NotNull(message = "Error callback URL is required")
    val errorCallBackUrl: String
)
