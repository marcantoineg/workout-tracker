package com.forkosey.workout_tracker.application

import com.forkosey.workout_tracker.domain.common.Id
import com.forkosey.workout_tracker.domain.exercise_set.ExerciseSet
import com.forkosey.workout_tracker.infrastructure.db.EntityNotFoundException
import com.forkosey.workout_tracker.infrastructure.db.exercise_set.ExerciseSetRepository

class ExerciseSetService {
    private val repository = ExerciseSetRepository()

    fun getById(id: Id): ExerciseSet {
        return repository.findOneById(id) ?: throw EntityNotFoundException()
    }

    fun create(set: ExerciseSet): ExerciseSet {
        return repository.insert(set)
    }

    fun update(id: Id, set: ExerciseSet): ExerciseSet {
        return repository.update(id, set)
    }

    fun delete(id: Id) {
        repository.delete(id)
    }
}