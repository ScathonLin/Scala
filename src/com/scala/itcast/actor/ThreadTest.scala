package com.scala.itcast.actor

import java.util.concurrent.{Executors, Callable}

/**
  * Created by linhd on 2017/12/5.
  */
object ThreadTest {
  def main(args: Array[String]) {
    val pool = Executors.newFixedThreadPool(2)
    val future = pool.submit(new Task)
    while (!future.isDone) {
      println(future.get())
    }
  }
}

class Task extends Callable[String] {
  override def call(): String = {
    Thread.sleep(2000)
    "linhd"
  }
}
