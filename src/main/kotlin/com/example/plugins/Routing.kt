package com.example.plugins

import com.example.dao.AddressDAO
import com.example.dao.OrderDAO
import com.example.routes.addressRouting
import com.example.routes.orderRouting
import com.example.service.AddressService
import com.example.service.OrderService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val orderService = OrderService(OrderDAO)
    val addressService = AddressService(AddressDAO())
    routing {
        orderRouting(orderService)
        addressRouting(addressService)
    }
}
