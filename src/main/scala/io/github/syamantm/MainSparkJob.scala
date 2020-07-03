package io.github.syamantm

import io.github.syamantm.config.AppConfig
import org.apache.spark.sql.{SaveMode, SparkSession}

object MainSparkJob {

  def run(dataset: String, config: AppConfig)(implicit spark: SparkSession) = {
    import spark.sqlContext.implicits._
    val hiveQueryRow = HiveQueryExtractor.
      extract(dataset, config)
      .head()

    val insertDF = CountingHiveTransformer.transform(hiveQueryRow)

    insertDF.show()

    insertDF.write
        .mode(SaveMode.Append)
      .jdbc(config.jdbcConfig.url, "ref_db.execution_result", config.jdbcConfig.toProps)

    insertDF

  }

}
