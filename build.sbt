ThisBuild / organization := "com.github.acsgh.common.scala"
ThisBuild / scalaVersion := "3.0.0"

import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

lazy val compilerOptions = Seq(
  "-encoding",
  "UTF-8",
  "-feature",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings"
)

lazy val commonSettings = Seq(
  scalacOptions := compilerOptions,
  sonatypeProfileName := "com.github.acsgh",
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    releaseStepCommandAndRemaining("+publishSigned"),
    releaseStepCommand("sonatypeBundleRelease"),
    setNextVersion,
    commitNextVersion,
    pushChanges
  ),
  crossScalaVersions := List("3.0.0"),
  releaseCrossBuild := true,
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  libraryDependencies ++= Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
    "org.slf4j" % "slf4j-api" % "1.7.31",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.scalatest" %% "scalatest" % "3.2.9" % Test,
    "org.scalacheck" %% "scalacheck" % "1.15.4" % Test,
    "org.pegdown" % "pegdown" % "1.6.0" % Test
  ),
  homepage := Some(url("https://github.com/acsgh/scala-common")),
  licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  publishMavenStyle := true,
  Test / publishArtifact := false,
  pomIncludeRepository := { _ => false },
  publishTo := sonatypePublishToBundle.value,
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/acsgh/scala-common"),
      "scm:git:git@github.com:acsgh/scala-common.git"
    )
  ),
  developers := List(
    Developer("acsgh", "Alberto Crespo", "albertocresposanchez@gmail.com", url("https://github.com/acsgh"))
  )
)


lazy val root = (project in file("."))
  .settings(
    name := "scala-common",
    commonSettings,
    crossScalaVersions := Nil,
    publish / skip := true
  )
  .aggregate(core)

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    commonSettings
  )