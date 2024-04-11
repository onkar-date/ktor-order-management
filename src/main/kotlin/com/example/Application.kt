package com.example

import com.example.plugins.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()

    val config = ConfigFactory.load()
    try {
        Database.connect(
            url = config.getString("ktor.database.url"),
            driver = config.getString("ktor.database.driver"),
            user = config.getString("ktor.database.user"),
            password = config.getString("ktor.database.password")
        )
        println("Database connection established successfully!")
    } catch (e: Exception) {
        println("Failed to connect to the database: ${e.message}")
    }
}
