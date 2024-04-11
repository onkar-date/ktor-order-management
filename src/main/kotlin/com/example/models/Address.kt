package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Address(
    var id: String? = null,
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val state: String,
    val zipcode: String
)

object Addresses : Table() {
    val id = varchar("id", 36)
    val addressLine1 = varchar("address_line1", 255)
    val addressLine2 = varchar("address_line2", 255)
    val city = varchar("city", 100)
    val state = varchar("state", 100)
    val zipcode = varchar("zipcode", 10)

    override val primaryKey = PrimaryKey(id, name = "PK_Address_ID")
}