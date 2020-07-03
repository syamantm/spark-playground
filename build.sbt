import Dependencies._

ThisBuild / scalaVersion     := "2.12.11"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.github"
ThisBuild / organizationName := "syamantm"

enablePlugins(DockerComposePlugin)

lazy val root = (project in file("."))
  .settings(
    name := "spark-playground",
    libraryDependencies ++= Seq(
      sparkCore,
      postgresJdbc,
      scalaTest,
      sparkTestBase,
      commonsIo
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
