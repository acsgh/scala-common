package com.acsgh.common.scala.lock

class ReentrantReadWriteLock extends java.util.concurrent.locks.ReentrantReadWriteLock {
  def secureRead[T](action: () => T): T = {
    readLock().lock()
    try {
      action()
    } finally {
      readLock().unlock()
    }
  }

  def secureWrite(action: () => Unit) {
    writeLock().lock()
    try {
      action()
    } finally {
      writeLock().unlock()
    }
  }

  def secureWrite[T](action: () => T): T = {
    writeLock().lock()
    try {
      action()
    } finally {
      writeLock().unlock()
    }
  }
}