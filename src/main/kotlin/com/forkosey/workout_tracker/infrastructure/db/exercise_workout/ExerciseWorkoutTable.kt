package com.forkosey.workout_tracker.infrastructure.db.exercise_workout

import com.forkosey.workout_tracker.domain.workout.ExerciseWorkoutJunction
import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.infrastructure.db.exercise.ExerciseTable
import com.forkosey.workout_tracker.infrastructure.db.workout.WorkoutTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object ExerciseWorkoutTable : Table("exercise_workout") {
    val workoutId = (uuid("workout_id") references WorkoutTable.id)
    val exerciseId = (uuid("workout_id") references ExerciseTable.id)

    fun toDomain(row: ResultRow): ExerciseWorkoutJunction {
        return ExerciseWorkoutJunction(
            exerciseId = Id(row[exerciseId]),
            workoutId = Id(row[workoutId])
        )
    }
}