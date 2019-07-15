package com.acsgh.common.scala

import java.util.concurrent.atomic.AtomicBoolean

import com.acsgh.common.scala.log.{LogLevel, LogSupport}
import com.acsgh.common.scala.time.StopWatchSupport

trait App extends LogSupport with StopWatchSupport {
  private var configureActions: List[() => Unit] = List()
  private var startActions: List[() => Unit] = List()
  private var stopActions: List[() => Unit] = List()

  private val started = new AtomicBoolean(false)

  val name: String

  def main(args: Array[String]): Unit = {
    sys.addShutdownHook {
      stop()
    }
    start()
  }

  def onConfigure(action: => Unit): Unit = configureActions = configureActions ++ List(() => action)

  def onStart(action: => Unit): Unit = startActions = startActions ++ List(() => action)

  def onStop(action: => Unit): Unit = stopActions = stopActions ++ List(() => action)

  def start(): Unit = {
    if (started.compareAndSet(false, true)) {
      time(s"Server $name started") {
        executeAll("Configure", configureActions)
        executeAll("Start", startActions)
      }
    }
  }

  def stop(): Unit = {
    if (started.compareAndSet(true, false)) {
      time(s"Server $name stopped") {
        executeAll("Stop", stopActions)
      }
    }
  }

  private def executeAll(actionName: String, actions: List[() => Unit]): Unit = {
    time(s"Stage $actionName done", LogLevel.TRACE) {
      try {
        actions.foreach(_ ())
      } catch {
        case t: Throwable =>
          log.warn(s"Unable to ${actionName.toLowerCase}", t)
          throw t
      }
    }
  }
}
