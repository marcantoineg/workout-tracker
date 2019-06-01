package com.forkosey.workout_tracker

import com.forkosey.workout_tracker.infrastructure.db.DatabaseFactory
import com.forkosey.workout_tracker.infrastructure.db.MigrationRunner
import com.forkosey.workout_tracker.interfaces.http.setup
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.forkosey.workout_tracker.interfaces.setup
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.routing.Routing
import io.ktor.util.KtorExperimentalAPI
import io.sentry.SentryUncaughtExceptionHandler.setup

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    when {
        isDev -> {
            install(CORS) {
                method(HttpMethod.Options)
                method(HttpMethod.Delete)
                method(HttpMethod.Put)
                header(HttpHeaders.Authorization)
                anyHost()
            }
        }
    }

    install(CallLogging)

    // Install the Locations module.
    install(Locations)

    // We configure jackson here.
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            registerModule(JavaTimeModule())
        }
    }

    install(DataConversion) {
        setup()
    }

    // The status page module is used to map exception to relevant status codes.
    install(StatusPages) {
        setup()
    }

    if (!testing) {
        val dbConfig = environment.config.config("ktor.db")
        DatabaseFactory.init(dbConfig)
        MigrationRunner.migrate(dbConfig)
    }

    install(Routing) {
        setup()
    }
}

val Application.envKind get() = environment.config.property("ktor.environment").getString()
val Application.isDev get() = envKind == "dev"
val Application.isProd get() = envKind != "dev"

