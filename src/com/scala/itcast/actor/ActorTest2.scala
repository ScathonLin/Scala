package com.scala.itcast.actor

import scala.actors.Actor

/**
  * Created by linhd on 2017/12/5.
  */
object ActorTest2 {
  def main(args: Array[String]) {
    val actorBuilder = new ActorBuilder()
    actorBuilder.start()
    val future1 = actorBuilder !! "getMsg"
    val result1 = future1.apply()
    println(result1)
    val future2 = actorBuilder !! "getMsg"
    val result2 = future2.apply()
    println(result2)
    println(future1 == future2)
    println("main函数发送消息完成")
  }
}

class ActorBuilder extends Actor {
  override def act(): Unit = {
    loop {
      react {
        case "getMsg" => println("test"); sender ! ReplyMessage(1, "linhd")
      }
    }
  }
}

case class ReplyMessage(id: Int, name: String)

