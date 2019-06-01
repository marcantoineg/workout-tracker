package com.forkosey.workout_tracker.infrastructure.db.exercise

import com.forkosey.workout_tracker.domain.Exercise
import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.infrastructure.db.exercise_set.ExerciseSetTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ExerciseRepository {

    fun findAll(): List<Exercise> {
        return transaction {
            ExerciseTable.selectAll().mapNotNull { ExerciseTable.toDomain(it) }
        }
    }

    fun findOneById(id: Id): Exercise? {
        return transaction {
            ExerciseTable.select { (ExerciseTable.id eq id.uuid) }
                .map { row ->
                    val sets = (ExerciseSetTable innerJoin ExerciseTable)
                        .select { (ExerciseTable.id eq id.uuid) }
                        .map { ExerciseSetTable.toDomain(it) }

                    ExerciseTable.toDomain(row).copy(sets = sets)
                }
                .singleOrNull()
        }
    }

    fun insert(exercise: Exercise): Exercise {
        return transaction {
            val id = ExerciseTable.insert {
                it[name] = exercise.name
                it[description] = exercise.description
            } get ExerciseTable.id
            findOneById(Id(id))!!
        }
    }

    fun update(id: Id, exercise: Exercise): Exercise {
        return transaction {
            ExerciseTable.update({ ExerciseTable.id eq id.uuid }) {
                it[name] = exercise.name
                it[description] = exercise.description
            }
            exercise
        }
    }

    fun delete(id: Id) {
        transaction {
            ExerciseTable.deleteWhere { ExerciseTable.id eq id.uuid }
        }
    }
}