package com.forkosey.workout_tracker.infrastructure.db.exercise

import com.forkosey.workout_tracker.domain.Exercise
import com.forkosey.workout_tracker.domain.common.Id
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object ExerciseTable : Table() {
    val id = uuid("id").primaryKey().autoIncrement()
    val name  = varchar("name", 255).default("")
    val description  = varchar("description", 255).nullable()

    fun toDomain(row: ResultRow): Exercise{
        return Exercise(
            id = Id(row[id]),
            name =  row[name],
            description = row[description]
        )
    }
}