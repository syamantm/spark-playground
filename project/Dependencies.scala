import sbt._

object Dependencies {
  lazy val scalaVersionMajor = "2.12"
  lazy val sparkVersion = "2.4.5"
  lazy val sparkTestBaseVersion = "0.14.0"
  lazy val postgresJdbcVersion = "42.2.14"

  lazy val sparkCore = "org.apache.spark" %% "spark-sql" % sparkVersion % Compile
  lazy val postgresJdbc = "org.postgresql" % "postgresql" % postgresJdbcVersion

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test
  lazy val sparkTestBase = "com.holdenkarau" % s"spark-testing-base_${scalaVersionMajor}" % s"${sparkVersion}_${sparkTestBaseVersion}" % Test
  lazy val commonsIo = "org.checkerframework.annotatedlib" % "commons-io" % "2.7" % Test
}
