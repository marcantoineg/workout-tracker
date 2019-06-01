package com.forkosey.workout_tracker.interfaces.http.routes

import com.forkosey.workout_tracker.application.ExerciseSetService
import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.domain.exercise_set.ExerciseSet
import io.ktor.application.call
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

@KtorExperimentalLocationsAPI
@Location("/{exerciseSetId}")
data class ExerciseSetDetail(val exerciseSetId: Id)

@KtorExperimentalLocationsAPI
fun Route.exerciseSets(exerciseSetService: ExerciseSetService) {

    route("/sets") {
        post("") {
            val set = call.receive<ExerciseSet>()
            call.respond(message = exerciseSetService.create(set))
        }

        get<ExerciseSetDetail> {
            call.respond(message = exerciseSetService.getById(it.exerciseSetId))
        }

        put<ExerciseSetDetail> {
            val set = call.receive<ExerciseSet>()
            call.respond(message = exerciseSetService.update(it.exerciseSetId, set))
        }

        delete<ExerciseSetDetail> {
            call.respond(message = exerciseSetService.delete(it.exerciseSetId))
        }
    }
}