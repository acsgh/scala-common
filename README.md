# scala-common
Common utils used in several projects

![Build Status](https://travis-ci.org/acsgh/scala-common.svg?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.acsgh.common.scala/core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.acsgh.common.scala%22%20AND%20a:%22core%22)

## Log support
Forget about initiate your log just extends your class / object with 

```scala
import com.acsgh.common.scala.log.LogSupport

object Foo extends LogSupport {
  log.info("Hello world")
}
```



## Stop Watch

Utility to measure time in your code

```scala
import com.acsgh.common.scala.time.StopWatch
import com.acsgh.common.scala.log.LogLevel

val stopWatch = StopWatch.createStarted()
try{
// YOUR CODE HERE
} finally {
  stopWatch.printElapseTime("Important stuff", log, LogLevel.INFO)
}
```

It will print:
'Important stuff in 234 milliseconds'

## Reentrant locks

Utility to run safe locking in scala

### Simple
```scala
import com.acsgh.common.scala.lock.ReentrantLock

val simpleLock = ReentrantLock()

simpleLock.run{
 1 + 1
}

val result = simpleLock.get {
 1 + 1
}
```

### Read/Write
```scala
import com.acsgh.common.scala.lock.ReentrantReadWriteLock

val simpleLock = ReentrantLock()

simpleLock.write{
 1 + 1
}

val writeResult = simpleLock.write {
 1 + 1
}

val readResult = simpleLock.read {
 1 + 1
}

```
