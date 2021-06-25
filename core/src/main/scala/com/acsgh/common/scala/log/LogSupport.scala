package com.acsgh.common.scala.log

import com.typesafe.scalalogging.Logger

enum LogLevel {
  case TRACE, DEBUG, INFO, WARN, ERROR
}

object LogSupport {
  protected def logText(level: LogLevel, log: Logger, text: String, params: Any*): Unit = {
    level match {
      case LogLevel.TRACE => log.trace(text, params.toArray)
      case LogLevel.DEBUG => log.debug(text, params.toArray)
      case LogLevel.INFO => log.info(text, params.toArray)
      case LogLevel.WARN => log.warn(text, params.toArray)
      case LogLevel.ERROR => log.error(text, params.toArray)
    }
  }
}

trait LogSupport {
  val log: Logger = Logger(getClass)

  protected def logText(level: LogLevel, text: String, params: Any*): Unit = LogSupport.logText(level, log, text, params: _*)

  protected def logText(level: LogLevel, log: Logger, text: String, params: Any*): Unit = LogSupport.logText(level, log, text, params: _*)
}