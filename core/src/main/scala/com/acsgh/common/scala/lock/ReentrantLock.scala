package com.acsgh.common.scala.lock

class ReentrantLock extends java.util.concurrent.locks.ReentrantLock {
  def get[T](action: => T): T = {
    lock()
    try {
      action
    } finally {
      unlock()
    }
  }

  def run(action: => Unit): Unit = {
    lock()
    try {
      action
    } finally {
      unlock()
    }
  }
}

