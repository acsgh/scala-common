package com.acsgh.common.scala.lock

class ReentrantLock extends java.util.concurrent.locks.ReentrantLock {
  def run[T](action:  => T): T = {
    lock()
    try {
      action
    } finally {
      unlock()
    }
  }

  def get(action:  => Unit) {
    lock()
    try {
      action
    } finally {
      unlock()
    }
  }
}

