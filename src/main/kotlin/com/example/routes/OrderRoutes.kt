package com.example.routes

import com.example.models.Order
import com.example.service.OrderService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRouting(orderService: OrderService) {
    route("/order") {

        get {
            call.respond(orderService.getAllOrders())
        }

        get("/{id}") {
            val orderId = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val order = orderService.getOrderById(orderId)
            if (order != null) {
                call.respond(order)
            } else {
                call.respond(HttpStatusCode.NotFound, "Order not found")
            }
        }

        post {
            val order = call.receive<Order>()
            orderService.createOrder(order)
            call.respond(HttpStatusCode.Created, "Order placed successfully")
        }
    }
}
