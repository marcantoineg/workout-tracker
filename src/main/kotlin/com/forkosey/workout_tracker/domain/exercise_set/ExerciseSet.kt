package com.forkosey.workout_tracker.domain.exercise_set

import com.forkosey.workout_tracker.domain.common.Id
import java.math.BigDecimal

data class ExerciseSet(
    val id: Id?,
    val repetition: Int = 0,
    val value: BigDecimal = 0.toBigDecimal(),
    val type: ExerciseSetTypes = ExerciseSetTypes.LBS
)