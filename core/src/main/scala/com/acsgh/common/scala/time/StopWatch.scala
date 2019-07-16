package com.acsgh.common.scala.time

import java.text.DecimalFormat
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

import com.acsgh.common.scala.log.{LogLevel, LogSupport}
import com.typesafe.scalalogging.Logger


object StopWatch {
  private val DEFAULT_FORMATTER = new DecimalFormat("#.###")

  def create(): StopWatch = StopWatch()

  def createStarted(): StopWatch = StopWatch().start()
}

case class StopWatch private() extends LogSupport {

  private val startTime = new AtomicLong(-1)

  def started = startTime.get() != -1


  def elapseTime: Long = {
    val start = startTime.get()
    if (start > 0) {
      System.currentTimeMillis() - start
    } else {
      throw new IllegalArgumentException("The stop watch haven't started yet")
    }
  }


  def dividedElapseTime: String = TimerSplitter.getIntervalInfo(Math.round(getElapseTime(TimeUnit.MILLISECONDS)), TimeUnit.MILLISECONDS)

  def start(): StopWatch = {
    startTime.set(System.currentTimeMillis())
    this
  }

  def stop(): StopWatch = {
    startTime.set(-1)
    this
  }

  def getElapseTime(unit: TimeUnit): Double = convertMillisecondTime(unit, elapseTime)

  def getElapseTimeFormatted(unit: TimeUnit, formatter: DecimalFormat = StopWatch.DEFAULT_FORMATTER): String = formatter.format(getElapseTime(unit))


  def printElapseTimeWithUnits(eventName: String, log: Logger, level: LogLevel, unit: TimeUnit, params: Any*) {
    val time = getElapseTimeFormatted(unit) + " " + unit.toString.toLowerCase()
    val text = s" in $time"
    logText(level, log, eventName + text, params: _*)
  }

  def printElapseTime(eventName: String, log: Logger, level: LogLevel, params: Any*) {
    val time = TimerSplitter.getIntervalInfo(Math.round(getElapseTime(TimeUnit.MILLISECONDS)), TimeUnit.MILLISECONDS)
    val text = s" in $time"
    logText(level, log, eventName + text, params: _*)
  }

  override def toString(): String = s"in ${TimerSplitter.getIntervalInfo(Math.round(getElapseTime(TimeUnit.MILLISECONDS)), TimeUnit.MILLISECONDS)}"

  private def convertMillisecondTime(unit: TimeUnit, time: Long): Double = {
    val scale = TimeUnit.MILLISECONDS.convert(1, unit).doubleValue()
    time / scale
  }
}
