package com.forkosey.workout_tracker.interfaces

import com.forkosey.workout_tracker.application.ExerciseService
import com.forkosey.workout_tracker.application.ExerciseSetService
import com.forkosey.workout_tracker.application.WorkoutService
import com.forkosey.workout_tracker.interfaces.http.routes.exerciseSets
import com.forkosey.workout_tracker.interfaces.http.routes.exercises
import com.forkosey.workout_tracker.interfaces.http.routes.workouts
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalLocationsAPI
@KtorExperimentalAPI
fun Routing.setup() {

    // Initialize the service with its dependencies
    val workoutService     = WorkoutService()
    val exerciseService    = ExerciseService()
    val exerciseSetService = ExerciseSetService()

    // Register routes
    route("/api/v1") {
        workouts(workoutService)
        exercises(exerciseService)
        exerciseSets(exerciseSetService)
    }

    route("/health-check") {
        get {
            call.respondText { "running fine" }
        }
    }

}
