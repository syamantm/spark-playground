import sbt._

object Dependencies {
  lazy val scalaVersionMajor = "2.12"
  lazy val sparkVersion = "2.4.5"
  lazy val sparkTestBaseVersion = "0.14.0"

  lazy val sparkCore = "org.apache.spark" %% "spark-sql" % sparkVersion % Compile

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test
  lazy val sparkTestBase = "com.holdenkarau" % s"spark-testing-base_${scalaVersionMajor}" % s"${sparkVersion}_${sparkTestBaseVersion}" % Test
}
