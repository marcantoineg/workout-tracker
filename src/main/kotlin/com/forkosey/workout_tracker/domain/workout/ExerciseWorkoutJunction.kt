package com.forkosey.workout_tracker.domain.workout

import com.forkosey.workout_tracker.domain.common.Id

data class ExerciseWorkoutJunction (
    val exerciseId: Id,
    val workoutId: Id
)