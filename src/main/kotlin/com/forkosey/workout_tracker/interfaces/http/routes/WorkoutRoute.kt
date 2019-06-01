package com.forkosey.workout_tracker.interfaces.http.routes

import com.forkosey.workout_tracker.application.WorkoutService
import com.forkosey.workout_tracker.domain.workout.Workout
import com.forkosey.workout_tracker.domain.common.Id
import io.ktor.application.call
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

@KtorExperimentalLocationsAPI
@Location("/{workoutId}")
data class WorkoutDetail(val workoutId: Id)

@KtorExperimentalLocationsAPI
fun Route.workouts(workoutService: WorkoutService) {

    route("/workouts") {
        get("") {
            call.respond(message = workoutService.getAll())
        }

        post("") {
            val wo = call.receive<Workout>()
            call.respond(message = workoutService.create(wo))
        }

        get<WorkoutDetail> {
            call.respond(message = workoutService.getById(it.workoutId))
        }

        put<WorkoutDetail> {
            val wo = call.receive<Workout>()
            call.respond(message = workoutService.update(it.workoutId, wo))
        }

        delete<WorkoutDetail> {
            call.respond(message = workoutService.delete(it.workoutId))
        }
    }
}