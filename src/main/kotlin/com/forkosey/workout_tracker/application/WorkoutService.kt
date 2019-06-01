package com.forkosey.workout_tracker.application

import com.forkosey.workout_tracker.domain.workout.Workout
import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.infrastructure.db.EntityNotFoundException
import com.forkosey.workout_tracker.infrastructure.db.workout.WorkoutRepository

class WorkoutService {
    private val repository = WorkoutRepository()

    fun getAll(): List<Workout> {
        return repository.findAll()
    }

    fun getById(id: Id): Workout {
        return repository.findOneById(id) ?: throw EntityNotFoundException()
    }

    fun create(workout: Workout): Workout {
        return repository.insert(workout)
    }

    fun update(id: Id, workout: Workout): Workout {
        return repository.update(id, workout)
    }

    fun delete(id: Id) {
        repository.delete(id)
    }
}