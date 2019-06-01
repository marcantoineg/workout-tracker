package com.forkosey.workout_tracker.domain.workout

import com.forkosey.workout_tracker.domain.Exercise
import com.forkosey.workout_tracker.domain.common.Id

data class Workout(
    val id: Id?,
    val name: String,
    val description: String?,
    val exercises: List<Exercise> = listOf()
)