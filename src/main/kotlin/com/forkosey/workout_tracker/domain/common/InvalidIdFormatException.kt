package com.forkosey.workout_tracker.domain.common

class InvalidIdFormatException(val id: String) : Exception() {
    override val message: String?
        get() = "Invalid id format: $id"
}