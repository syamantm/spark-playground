package io.github.syamantm

import java.io.IOException
import java.nio.file.Paths

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import io.github.syamantm.config.{AppConfig, JdbcConfig}
import org.apache.commons.io.FileUtils
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MainSparkJobSpec extends AnyFlatSpec with Matchers with DataFrameSuiteBase {
  "The main spark job" should "report execution count" in {
    implicit val _spark = spark
    val database = "reporting_db"
    setUpDb(database)
    setUpTables(database)
    val config = AppConfig(
      jdbcConfig = JdbcConfig(
        url = "jdbc:postgresql://localhost:5432/ref_db",
        username = "postgres",
        password = "postgres",
        driver = "org.postgresql.Driver"
      )
    )

    val df = MainSparkJob.run("activities", config)

    df.head().getAs[Long]("value") should be(6L)
  }

  it should "count activities" in {
    val database = "reporting_db"
    setUpDb(database)
    setUpTables(database)

    implicit val _spark = spark
    val campaign = "my-campaign-1"
    val impressionsCount = CountingJob.countActivities(campaign, database)

    impressionsCount shouldBe(3)
  }

  protected def setUpDb(database: String) = {
    spark.sql(s"CREATE DATABASE IF NOT EXISTS ${database} LOCATION '/tmp/reporting_db.db'")
    spark.sql(s"DROP TABLE IF EXISTS ${database}.activities")
    try {
      val filePath = Paths.get("/tmp/reporting_db.db/activities")
      FileUtils.deleteDirectory(filePath.toFile)
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }

  protected def setUpTables(database: String) = {
    val activitiesSchema = StructType(Array(
      StructField("campaign", StringType, nullable = false),
      StructField("ad", StringType, nullable = false),
      StructField("impressions", LongType, nullable = false),
      StructField("click", LongType, nullable = false)
    ))

    val activitiesDF = spark.read.schema(activitiesSchema)
      .option("header", "true")
      .option("inferSchema", "false")
      .csv(getClass.getResource("/activities.csv").getPath)

    activitiesDF.write.mode(SaveMode.Overwrite).saveAsTable(s"${database}.activities")
  }
}
