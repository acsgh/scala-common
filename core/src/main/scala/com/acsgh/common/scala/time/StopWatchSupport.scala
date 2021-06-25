package com.acsgh.common.scala.time

import com.acsgh.common.scala.log.{LogLevel, LogSupport}

trait StopWatchSupport extends LogSupport {

  protected def time[T](text: String, level: LogLevel = LogLevel.INFO)(action: => T): T = {
    val stopWatch = StopWatch.createStarted()
    try {
      action
    } finally {
      stopWatch.printElapseTime(text, log, level)
    }
  }

  protected def timeManual(text: String, level: LogLevel = LogLevel.INFO): () => Unit = {
    val stopWatch = StopWatch.createStarted()
    () => stopWatch.printElapseTime(text, log, level)
  }
}