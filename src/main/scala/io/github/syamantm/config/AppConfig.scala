package io.github.syamantm.config

import java.util.Properties

case class JdbcConfig(url: String, username: String, password: String, driver: String) {
  def toProps = {
    val props = new Properties()
    props.put("driver", driver)
    props.put("user", username)
    props.put("password", password)
    props
  }
}

case class AppConfig(jdbcConfig: JdbcConfig)
