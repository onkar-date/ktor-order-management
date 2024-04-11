package com.example.dao

import com.example.models.Address
import com.example.models.Addresses
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class AddressDAO {

    fun findAll(): List<Address> {
        return transaction {
            Addresses.selectAll()
                .map { rowToAddress(it) }
        }
    }

    fun findById(id: String): Address? {
        return transaction {
            val resultRow = Addresses.select { Addresses.id eq id }.singleOrNull()
            resultRow?.let { rowToAddress(it) }
        }
    }

    fun create(address: Address) {
        transaction {
            Addresses.insert {
                it[id] = address.id.toString()
                it[addressLine1] = address.addressLine1
                it[addressLine2] = address.addressLine2
                it[city] = address.city
                it[state] = address.state
                it[zipcode] = address.zipcode
            }
        }
    }

    private fun rowToAddress(row: ResultRow): Address {
        return Address(
            id = row[Addresses.id].toString(),
            addressLine1 = row[Addresses.addressLine1],
            addressLine2 = row[Addresses.addressLine2],
            city = row[Addresses.city],
            state = row[Addresses.state],
            zipcode = row[Addresses.zipcode]
        )
    }
}