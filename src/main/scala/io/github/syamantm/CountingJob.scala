package io.github.syamantm

import org.apache.spark.sql.SparkSession

object CountingJob {

  def countActivities(campaign: String, database: String)(implicit spark: SparkSession) = {
    val df = spark.sql(s"SELECT count(impressions) AS total_impressions FROM ${database}.activities WHERE campaign = '${campaign}'")
    df.show()
    df.head().getAs[Long]("total_impressions")
  }
}
