package io.github.devcavin.kollect.dto.response

data class SafaricomStkPushResponse(
    val MerchantRequestId: String,
    val CheckoutRequestId: String,
    val ResponseCode: String,
    val ResponseDescription: String,
    val CustomerMessage: String
)