package io.github.syamantm

import org.apache.spark.sql.SparkSession

object Main {

  def main(args: Array[String]) {
    val logFile = s"${sys.props("user.dir")}/README.md" // Should be some file on your system
    val spark = buildSparkSession(args)
    val (numAs, numBs) = countNums(spark, logFile)
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }

  def countNums(spark: SparkSession, logFile: String) = {
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    (numAs, numBs)
  }

  private def buildSparkSession(args: Array[String]) = {
    val spark = SparkSession.builder
      .master("local")
      .appName("Simple Application")
      .getOrCreate()
    spark
  }
}
