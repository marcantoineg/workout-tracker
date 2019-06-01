package com.forkosey.workout_tracker.domain.common

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import java.util.*

data class Id(val uuid: UUID) {

    @JsonValue
    fun asString(): String {
        return uuid.toString()
    }

    companion object {
        @JvmStatic
        @JsonCreator
        fun fromString(value: String?): Id? {
            if (value != null && value.isNotBlank()) {
                try {
                    val uuid = UUID.fromString(value)
                    return Id(uuid)
                } catch (e: IllegalArgumentException) {
                    throw InvalidIdFormatException(value)
                }
            }
            return null
        }
    }
}