package com.example.service

import com.example.dao.OrderDAO
import com.example.models.Order
import java.util.*

class OrderService (private val orderDAO: OrderDAO) {

    fun getAllOrders(): List<Order> {
        return orderDAO.findAll();
    }

    fun getOrderById(id: String): Order? {
        return orderDAO.findById(id)
    }

    fun createOrder(order: Order) {
        order.orderId = UUID.randomUUID().toString()
        orderDAO.create(order)
    }
}