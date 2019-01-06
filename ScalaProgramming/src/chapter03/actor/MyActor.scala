package chapter03.actor

import scala.actors.Actor

/**
  * Created by linhd on 2018/3/7.
  */
class MyActor extends Actor {
  override def act(): Unit = {
    loop {
      react {
        case "start" => {
          println("starting...")
          Thread.sleep(5000)
          println("started...")
        }
        case "stop" => {
          println("stoping...")
          Thread.sleep(5000)
          println("stopped...")
        }
      }
    }
    //      receive {
    //        case "start" => {
    //          println("starting...")
    //          Thread.sleep(5000)
    //          println("started...")
    //        }
    //        case "stop" => {
    //          println("stoping...")
    //          Thread.sleep(5000)
    //          println("stopped...")
    //        }
    //      }
  }
}

object MyActor {
  def main(args: Array[String]): Unit = {
    val actor = new MyActor
    actor.start()
    actor ! "start"
    actor ! "stop"
    println("消息发送完成...")
  }
}
