package chapter03.actor

import scala.actors.Actor

/**
  * Created by linhd on 2018/3/7.
  */
object MyActor1 extends Actor {
  override def act(): Unit = {
    for (i <- 1 to 10) {
      println("actor-1" + i)
      Thread.sleep(1500)
    }
  }
}

object MyActor2 extends Actor {
  override def act(): Unit = {
    for (i <- 1 to 10) {
      println("actor-2" + i)
      Thread.sleep(1500)
    }
  }


}

object ActorTest extends App {
  MyActor1.start()
  MyActor2.start()
}
