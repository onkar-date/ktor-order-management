package com.example.routes

import com.example.dao.AddressDAO
import com.example.models.Address
import com.example.service.AddressService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.addressRouting(addressService: AddressService) {

    route("/address") {
        get {
            call.respond(addressService.getAllAddress())
        }

        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing ID parameter")
            val address = addressService.getAddressById(id)
            if (address != null) {
                call.respond(address)
            } else {
                call.respond(HttpStatusCode.NotFound, "Address not found")
            }
        }

        post {
            val address = call.receive<Address>()
            addressService.createAddress(address)
            call.respond(HttpStatusCode.Created, "Address added successfully")
        }
    }
}