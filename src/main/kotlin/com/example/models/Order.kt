package com.example.models

import com.example.customserializers.BigDecimalSerializer
import com.example.enums.OrderStatus
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal
import org.jetbrains.exposed.sql.javatime.date

@Serializable
data class Order(
    var orderId: String? = null,
    val orderDate: LocalDate,
    val quantity: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
    val orderStatus: OrderStatus,
    val shipToAddressId: String
)

object Orders: Table() {
    val orderId = varchar("id", 36)
    val orderDate = date("order_date")
    val quantity = integer("quantity")
    val amount = decimal("amount", 10, 2)
    val orderStatus = enumerationByName("status", 10, OrderStatus::class)
    val shipToAddressId = reference("ship_to_address_id", Addresses.id)
}
