# spark-playground

Spark play ground to try out Spark SQL, JDBC and unit testing

Main entrypoint is [MainSparkJob.scala](src/main/scala/io/github/syamantm/MainSparkJob.scala) which executes the spark queries against a
postgres database and hive.

## Usage

### Run tests

```bash
sbt clean dockerComposeUp test
```

### Clean up
```bash
docker-compose down --remove-orphans
``` 
