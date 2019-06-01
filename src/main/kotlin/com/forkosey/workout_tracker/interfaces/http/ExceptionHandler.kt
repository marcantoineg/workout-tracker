package com.forkosey.workout_tracker.interfaces.http

import com.forkosey.workout_tracker.infrastructure.db.EntityNotFoundException
import io.ktor.application.call
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

fun StatusPages.Configuration.setup() {
    exception<EntityNotFoundException> {
        call.respond(HttpStatusCode.NotFound)
    }
    exception<IllegalArgumentException> { cause ->
        call.respond(HttpStatusCode.BadRequest)
        throw cause
    }
}
