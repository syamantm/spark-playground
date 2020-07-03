package io.github.syamantm

import java.sql.Timestamp
import java.time.LocalDateTime

import org.apache.spark.sql.{Row, SparkSession}

object CountingHiveTransformer {

  def transform(executionRow: Row)(implicit spark: SparkSession) = {
    import spark.implicits._
    val timestamp = LocalDateTime.now();
    val query = executionRow.getAs[String]("query")
    val countColumn = executionRow.getAs[String]("count_column")
    val datasetName = executionRow.getAs[String]("dataset_name")
    val df = spark.sql(query)
    df.show()
    val count = df.head().getAs[Long](countColumn)
    Seq((datasetName, Timestamp.valueOf(timestamp), count)).toDF("dataset_name", "execution_ts", "value")
  }
}
