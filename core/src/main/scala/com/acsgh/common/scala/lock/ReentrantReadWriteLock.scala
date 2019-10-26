package com.acsgh.common.scala.lock

class ReentrantReadWriteLock extends java.util.concurrent.locks.ReentrantReadWriteLock {
  def read[T](action: => T): T = {
    readLock().lock()
    try {
      action
    } finally {
      readLock().unlock()
    }
  }

  def write(action: => Unit): Unit = {
    writeLock().lock()
    try {
      action
    } finally {
      writeLock().unlock()
    }
  }

  def write[T](action: => T): T = {
    writeLock().lock()
    try {
      action
    } finally {
      writeLock().unlock()
    }
  }
}