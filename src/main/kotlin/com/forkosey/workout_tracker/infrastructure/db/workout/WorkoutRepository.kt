package com.forkosey.workout_tracker.infrastructure.db.workout

import com.forkosey.workout_tracker.domain.workout.Workout
import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.infrastructure.db.exercise.ExerciseTable
import com.forkosey.workout_tracker.infrastructure.db.exercise_workout.ExerciseWorkoutTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class WorkoutRepository {

    fun findAll(): List<Workout> {
        return transaction {
            WorkoutTable.selectAll().mapNotNull { WorkoutTable.toDomain(it) }
        }
    }

    fun findOneById(id: Id): Workout? {
        return transaction {
            WorkoutTable.select { (WorkoutTable.id eq id.uuid) }
                .map { row ->
                    val exercises = (ExerciseTable innerJoin ExerciseWorkoutTable)
                        .select { (ExerciseWorkoutTable.workoutId eq id.uuid) }
                        .map { ExerciseTable.toDomain(it) }

                    WorkoutTable.toDomain(row).copy(exercises = exercises)
                }
                .singleOrNull()
        }
    }

    fun insert(workout: Workout): Workout {
        return transaction {
            val id = WorkoutTable.insert {
                it[name] = workout.name
                it[description] = workout.description
            } get WorkoutTable.id
            findOneById(Id(id))!!
        }
    }

    fun update(id: Id, workout: Workout): Workout {
        return transaction {
            WorkoutTable.update({ WorkoutTable.id eq id.uuid }) {
                it[name] = workout.name
                it[description] = workout.description
            }
            workout
        }
    }

    fun delete(id: Id) {
        transaction { WorkoutTable.deleteWhere { (WorkoutTable.id eq id.uuid) } }
    }
}