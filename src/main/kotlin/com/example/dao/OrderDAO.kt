package com.example.dao

import com.example.models.Order
import com.example.models.Orders
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object OrderDAO {
    fun findAll(): List<Order> {
        return transaction {
            Orders.selectAll()
                .map { rowToOrder(it) }
        }
    }

    fun findById(id: String): Order? {
        return transaction {
            val resultRow = Orders.select { Orders.orderId eq id }.singleOrNull()
            resultRow?.let { rowToOrder(it) }
        }
    }

    fun create(order: Order) {
        transaction {
            Orders.insert {
                it[orderId] = order.orderId.toString()
                it[orderDate] = order.orderDate.toJavaLocalDate()
                it[quantity] = order.quantity
                it[amount] = order.amount
                it[orderStatus] = order.orderStatus
                it[shipToAddressId] = order.shipToAddressId
            }
        }
    }

    private fun rowToOrder(row: ResultRow): Order {

        return Order(
            orderId = row[Orders.orderId].toString(),
            orderDate = row[Orders.orderDate].toKotlinLocalDate(),
            quantity = row[Orders.quantity],
            amount = row[Orders.amount],
            orderStatus = row[Orders.orderStatus],
            shipToAddressId = row[Orders.shipToAddressId]
        )
    }
}