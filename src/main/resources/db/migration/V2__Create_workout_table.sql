CREATE TABLE "workout" (
    id          UUID         NOT NULL PRIMARY KEY DEFAULT uuid_generate_v1mc(),
    name        VARCHAR(255) NOT NULL             DEFAULT '',
    description VARCHAR(255)                      DEFAULT NULL
);

CREATE TABLE "exercise" (
    id          UUID         NOT NULL PRIMARY KEY DEFAULT uuid_generate_v1mc(),
    name        VARCHAR(255) NOT NULL             DEFAULT '',
    description VARCHAR(255)                      DEFAULT NULL
);

CREATE TABLE "exercise_workout" (
    workout_id  UUID NOT NULL REFERENCES "workout"(id),
    exercise_id UUID NOT NULL REFERENCES "exercise"(id),
    CONSTRAINT exercise_workout_pk PRIMARY KEY (workout_id, exercise_id)
);

CREATE TABLE "exercise_set" (
     id          UUID               NOT NULL PRIMARY KEY               DEFAULT uuid_generate_v1mc(),
     repetition  NUMERIC            NOT NULL                           DEFAULT 0,
     value       NUMERIC(11, 2)     NOT NULL                           DEFAULT 0,
     type        VARCHAR(50)        NOT NULL                           DEFAULT 'LBS',
     exercise_id UUID               NOT NULL REFERENCES "exercise"(id)
);