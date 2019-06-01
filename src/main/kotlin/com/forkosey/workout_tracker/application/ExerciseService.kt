package com.forkosey.workout_tracker.application

import com.forkosey.workout_tracker.domain.Exercise
import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.infrastructure.db.EntityNotFoundException
import com.forkosey.workout_tracker.infrastructure.db.exercise.ExerciseRepository

class ExerciseService {
    val repository = ExerciseRepository()

    fun getAll(): List<Exercise> {
        return repository.findAll()
    }

    fun getById(id: Id): Exercise {
        return repository.findOneById(id) ?: throw EntityNotFoundException()
    }

    fun create(exercise: Exercise): Exercise {
        return repository.insert(exercise)
    }

    fun update(id: Id, exercise: Exercise): Exercise {
        return repository.update(id, exercise)
    }

    fun delete(id: Id) {
        repository.delete(id)
    }
}