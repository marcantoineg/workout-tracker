package com.forkosey.workout_tracker.infrastructure.db

import io.ktor.config.ApplicationConfig
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.Database

@KtorExperimentalAPI
object DatabaseFactory {
    fun init(dbConfig: ApplicationConfig) {
        val host = dbConfig.property("host").getString()
        val port = dbConfig.property("port").getString()
        val database = dbConfig.property("database").getString()
        val user = dbConfig.property("user").getString()
        val password = dbConfig.property("password").getString()
        val sslmode = dbConfig.property("sslmode").getString()
        Database.connect(
            "jdbc:postgresql://$host:$port/$database?sslmode=$sslmode",
            driver = "org.postgresql.Driver",
            user = user,
            password = password
        )
    }
}
