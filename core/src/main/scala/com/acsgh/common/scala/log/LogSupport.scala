package com.acsgh.common.scala.log

import com.typesafe.scalalogging.Logger
import enumeratum.{Enum, EnumEntry}


sealed trait LogLevel extends EnumEntry

object LogLevel extends Enum[LogLevel] {

  val values = findValues

  case object TRACE extends LogLevel

  case object DEBUG extends LogLevel

  case object INFO extends LogLevel

  case object WARN extends LogLevel

  case object ERROR extends LogLevel

}

trait LogSupport {
  val log: Logger = Logger(getClass)

  protected def logText(level: LogLevel, text: String, params: Any*) {
    level match {
      case LogLevel.TRACE => log.trace(text, params.toArray)
      case LogLevel.DEBUG => log.debug(text, params.toArray)
      case LogLevel.INFO => log.info(text, params.toArray)
      case LogLevel.WARN => log.warn(text, params.toArray)
      case LogLevel.ERROR => log.error(text, params.toArray)
    }
  }
}