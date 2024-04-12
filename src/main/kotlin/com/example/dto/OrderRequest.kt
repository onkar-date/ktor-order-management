package com.example.dto

import com.example.customserializers.BigDecimalSerializer
import com.example.enums.OrderStatus
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class OrderRequest (
    var orderId: String? = null,
    val orderDate: LocalDate,
    val quantity: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
    val orderStatus: OrderStatus,
    val shipToAddressId: String
)