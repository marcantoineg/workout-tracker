WITH wo AS (
    INSERT INTO workout (name, description)
        VALUES (
            'Chest Workout',
            'This is a nice chest workout'
        )
        RETURNING id
),
ex AS (
    INSERT INTO exercise(name, description)
    VALUES (
            'Flat bench press',
            'Simple flat bench press duh'
       ),
       (
           'Incline dumbbell press',
           '45 degree angled dumbbell press'
       )
       RETURNING id, name
),
set1 AS (
    INSERT INTO exercise_set(repetition, value, type, exercise_id)
    SELECT 12, 135, 'lbs', ex.id FROM ex WHERE ex.name = 'Flat bench press'
),
set2 AS (
    INSERT INTO exercise_set(repetition, value, type, exercise_id)
    SELECT 12, 60, 'lbs', ex.id FROM ex WHERE ex.name = 'Incline dumbbell press'
)
INSERT INTO exercise_workout (workout_id, exercise_id)
SELECT wo.id, ex.id FROM wo, ex;


WITH wo AS (
    INSERT INTO workout (name, description)
        VALUES (
                   'Back Workout',
                   'This is a nice back workout'
               )
        RETURNING id
),
ex AS (
    INSERT INTO exercise(name, description)
    VALUES (
        'Wide lat pull down',
        'Simple lat pull down'
    ),
     (
        'Cable row',
        'Simple cable row'
     )
    RETURNING id, name
),
 set1 AS (
    INSERT INTO exercise_set(repetition, value, type, exercise_id)
    SELECT 12, 115, 'lbs', ex.id FROM ex
)
INSERT INTO exercise_workout (workout_id, exercise_id)
SELECT wo.id, ex.id FROM wo, ex;

TRUNCATE exercise_set CASCADE;
TRUNCATE exercise CASCADE;
TRUNCATE workout CASCADE;