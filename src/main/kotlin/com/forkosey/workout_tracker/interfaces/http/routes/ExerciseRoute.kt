package com.forkosey.workout_tracker.interfaces.http.routes

import com.forkosey.workout_tracker.application.ExerciseService
import com.forkosey.workout_tracker.domain.Exercise
import com.forkosey.workout_tracker.domain.common.Id
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.delete
import io.ktor.locations.get
import io.ktor.locations.put
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

@KtorExperimentalLocationsAPI
@Location("/{exerciseId}")
data class ExerciseDetail(val exerciseId: Id)

@KtorExperimentalLocationsAPI
fun Route.exercises(exerciseService: ExerciseService) {

    route("/exercises") {
        get("") {
            call.respond(message = exerciseService.getAll())
        }

        post("") {
            val ex = call.receive<Exercise>()
            call.respond(message = exerciseService.create(ex))
        }

        get<ExerciseDetail> {
            call.respond(message = exerciseService.getById(it.exerciseId))
        }

        put<ExerciseDetail> {
            val ex = call.receive<Exercise>()
            call.respond(message = exerciseService.update(it.exerciseId, ex))
        }

        delete<ExerciseDetail> {
            call.respond(message = exerciseService.delete(it.exerciseId))
        }
    }
}