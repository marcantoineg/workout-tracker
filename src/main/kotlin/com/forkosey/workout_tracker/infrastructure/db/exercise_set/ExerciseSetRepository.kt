package com.forkosey.workout_tracker.infrastructure.db.exercise_set

import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.domain.exercise_set.ExerciseSet
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ExerciseSetRepository {

    fun findOneById(id: Id): ExerciseSet? {
        return transaction {
            ExerciseSetTable.select { (ExerciseSetTable.id eq id.uuid) }
                .map { ExerciseSetTable.toDomain(it) }
                .singleOrNull()
        }
    }

    fun insert(set: ExerciseSet): ExerciseSet {
        return transaction {
            val id = ExerciseSetTable.insert {
                it[repetition] = set.repetition
                it[value] = set.value
                it[type] = set.type.value
            } get ExerciseSetTable.id
            findOneById(Id(id))!!
        }
    }

    fun update(id: Id, set: ExerciseSet): ExerciseSet {
        return transaction {
            ExerciseSetTable.update({ ExerciseSetTable.id eq id.uuid }) {
                it[repetition] = set.repetition
                it[value] = set.value
                it[type] = set.type.value
            }
            set
        }
    }

    fun delete(id: Id) {
        transaction {
            ExerciseSetTable.deleteWhere { ExerciseSetTable.id eq id.uuid }
        }
    }
}