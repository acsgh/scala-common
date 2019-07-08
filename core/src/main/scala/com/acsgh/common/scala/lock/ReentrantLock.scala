package com.acsgh.common.scala.lock

class ReentrantLock extends java.util.concurrent.locks.ReentrantLock {
  def secure[T](action: () => T): T = {
    lock()
    try {
      action()
    } finally {
      unlock()
    }
  }

  def secure(action: () => Unit) {
    lock()
    try {
      action()
    } finally {
      unlock()
    }
  }
}

