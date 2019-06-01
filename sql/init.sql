DROP DATABASE IF EXISTS "workout_tracker_db";
DROP ROLE IF EXISTS "workout-tracker-app";

CREATE DATABASE workout_tracker_db;

CREATE ROLE "workout-tracker-app" WITH PASSWORD 'letempypassw0rd' LOGIN INHERIT;

GRANT ALL ON DATABASE workout_tracker_db TO "workout-tracker-app";

\c workout_tracker_db

GRANT USAGE ON SCHEMA "public" TO "workout-tracker-app";
CREATE EXTENSION "uuid-ossp";