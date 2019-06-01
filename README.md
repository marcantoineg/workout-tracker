# Workout tracker api
This is a simple kotlin rest api server.

Special thanks to Kim Desrosiers for the template. (He's a great guy, go check him out on [github](https://github.com/kimond)!)

## Requirements
* Java 8
* Postgresql 9.6+

## Stack
This template use the following libraries.
* [Kotlin](https://kotlinlang.org/) - JVM based language
* [Ktor](https://ktor.io/) - Framework for building asynchronous servers and clients
* [Exposed](https://github.com/JetBrains/Exposed) - Kotlin SQL Library
* [Flyway](https://flywaydb.org/) - Database migrations tool
* [PostgreSQL](https://www.postgresql.org/) - Relational Database

### Test Stack
* [MockK](https://mockk.io/) - mocking library for Kotlin

### Project structure
* **application**: This is where we call repositories and external api.
  This is the glue code where the most business logic is executed.
  
* **domain**: We define our domain objects here. 

* **infrastructure**: Everything related to the infrastructure such databases and
  external services is handled here.
  
* **server**: This is where we define the exposed part our the service. e.g. routes.

### Using Intellij IDEA
1. Click `File -> New -> Project from existing sources...`
2. Select the new service folder.
3. Choose `Import source from external model -> Graddle`
4. Keep default parameters if everything is ok. Activate `auto import`
5. Intellij will setup everything.

### DB setup
* Run the `sql/init.sql` to create the database and the role.
  ```
    psql -U <user> -h 127.0.0.1 < sql/init.sql
  ```

### Start the application
To start the application run the following command. You can setup a run configuration
in your IDE as well.
```
gradlew run
```




