package com.scala.itcast.actor

import scala.actors.Actor

/**
  * Created by linhd on 2017/12/5.
  */
class MyActor1 extends Actor {
   override def act(): Unit = {
      for (i <- 1 to 10) {
         println("actor1..")
         Thread.sleep(1000)
      }
   }
}

class MyActor2 extends Actor {
   override def act(): Unit = {
      for (i <- 1 to 10) {
         println("actor2..")
         Thread.sleep(1000)
      }
   }
}

object MyActor1 {
   def main(args: Array[String]) {
      new MyActor1().start()
      new MyActor2().start()
   }
}
