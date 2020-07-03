package io.github.syamantm

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MainSpec extends AnyFlatSpec with Matchers with DataFrameSuiteBase {
  "The main object" should "count words" in {
    val logFile = s"${sys.props("user.dir")}/src/test/resources/testLog.txt"

    val (numAs, numBs) = Main.countNums(spark, logFile)

    numAs shouldBe (3)
    numBs shouldBe (1)
  }
}
