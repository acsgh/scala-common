package com.acsgh.common.scala

import java.time.{LocalDateTime, ZoneId, ZoneOffset, ZonedDateTime}

extension (input: LocalDateTime) {
  def toZone(zoneId: ZoneId): LocalDateTime = ZonedDateTime.of(input, ZoneOffset.UTC).withZoneSameInstant(zoneId).toLocalDateTime

  def toUTC(zoneId: ZoneId): LocalDateTime = ZonedDateTime.of(input, zoneId).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime
}
