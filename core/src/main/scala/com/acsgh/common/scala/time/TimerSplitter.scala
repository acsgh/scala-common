package com.acsgh.common.scala.time

import java.util.concurrent.TimeUnit

object TimerSplitter {
  def getIntervalInfo(intervalDuration: Long, unit: TimeUnit): String = {
    val values = getLongValues(intervalDuration, unit)
    convertTimeValuesToString(intervalDuration, unit, values)
  }

  private def convertTimeValuesToString(intervalDuration: Long, unit: TimeUnit, values: List[Long]): String = {
    val timeUnits = TimeUnit.values()
    var builder = ""

    for (i <- timeUnits.size - 1 to 0 by -1) {
      val valueTemp = values(i)
      val unitTemp = timeUnits(i)


      if (valueTemp > 0) {
        if (builder.nonEmpty) {
          builder += ", "
        }
        builder += getValueString(valueTemp, unitTemp)
      }
    }

    if (builder.isEmpty) {
      builder += getValueString(intervalDuration, unit)
    }

    builder
  }

  private def getLongValues(intervalDuration: Long, unit: TimeUnit): List[Long] = {
    val timeUnits = TimeUnit.values()
    val values = timeUnits.map(u => if (u == unit) intervalDuration else 0)


    for (i <- 0 until (timeUnits.size - 1)) {
      val value = values(i)
      val nexUnitValue = timeUnits(i).convert(1, timeUnits(i + 1))

      val nextUnitDelta = value / nexUnitValue
      val remainder = value % nexUnitValue

      values(i) = remainder
      values(i + 1) = values(i + 1) + nextUnitDelta
    }

    values.toList
  }

  private def getValueString(value: Long, unit: TimeUnit): String = {
    var result = s"$value ${unit.toString.toLowerCase()}"

    if (value == 1L) {
      result = result.substring(0, result.length - 1)
    }

    result
  }
}
