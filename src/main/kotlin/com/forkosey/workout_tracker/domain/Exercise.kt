package com.forkosey.workout_tracker.domain

import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.domain.exercise_set.ExerciseSet

data class Exercise(
    val id: Id?,
    val name: String,
    val description: String?,
    val sets: List<ExerciseSet> = listOf()
)