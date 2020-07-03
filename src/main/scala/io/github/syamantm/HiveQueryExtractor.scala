package io.github.syamantm

import io.github.syamantm.config.AppConfig
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, lit}

object HiveQueryExtractor {

  def extract(dataset: String, config: AppConfig)(implicit spark: SparkSession) = {
    val hiveQueryTable = spark
      .read
      .jdbc(config.jdbcConfig.url, "ref_db.hive_query", config.jdbcConfig.toProps)

    val resultDF = hiveQueryTable.
      where(
        col("dataset_name") === lit(dataset)).
      select(
        col("dataset_name"),
        col("query"),
        col("count_column"),
        col("min_val"),
        col("max_val")
      )
    resultDF.show()
    resultDF
  }
}
