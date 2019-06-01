package com.forkosey.workout_tracker.infrastructure.db

import io.ktor.config.ApplicationConfig
import io.ktor.util.KtorExperimentalAPI
import org.flywaydb.core.Flyway

@KtorExperimentalAPI
object MigrationRunner {
    fun migrate(dbConfig: ApplicationConfig) {
        val host = dbConfig.property("host").getString()
        val port = dbConfig.property("port").getString()
        val database = dbConfig.property("database").getString()
        val user = dbConfig.property("user").getString()
        val password = dbConfig.property("password").getString()
        val sslmode = dbConfig.property("sslmode").getString()
        val flyway = Flyway.configure()
            .baselineOnMigrate(true)
            .dataSource("jdbc:postgresql://$host:$port/$database?sslmode=$sslmode", user, password)
            .load()

        flyway.migrate()
    }
}
