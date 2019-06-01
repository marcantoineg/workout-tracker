package com.forkosey.workout_tracker.infrastructure.db.exercise_set

import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.domain.exercise_set.ExerciseSet
import com.forkosey.workout_tracker.domain.exercise_set.ExerciseSetTypes
import com.forkosey.workout_tracker.infrastructure.db.exercise.ExerciseTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object ExerciseSetTable : Table("exercise_set") {
    val id = uuid("id").primaryKey().autoIncrement()
    val repetition = integer("repetition").default(0)
    val value = decimal("value", precision = 2, scale = 11).default(0.toBigDecimal())
    val type  = varchar("type", 50).default("LBS")
    val exerciseId = (uuid("exercise_id") references ExerciseTable.id)

    fun toDomain(row: ResultRow): ExerciseSet {
        return ExerciseSet(
            id = Id(row[id]),
            repetition = row[repetition],
            value = row[value],
            type = ExerciseSetTypes.valueOf(row[type])
        )
    }
}