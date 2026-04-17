package io.github.devcavin.kollect.dto.request

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class AccountStatementRequest(
    @field:NotNull
    @JsonFormat(pattern = "yyyyMMdd")
    val startDate: LocalDate,

    @field:NotNull
    @JsonFormat(pattern = "yyyyMMdd")
    val endDate: LocalDate
) {
    init {
        require(!startDate.isAfter(endDate)) {
            "Start date must not be after end date"
        }
    }
}