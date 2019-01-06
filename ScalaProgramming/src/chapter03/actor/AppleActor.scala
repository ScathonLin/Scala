package chapter03.actor

import scala.actors.Actor

/**
  * Created by linhd on 2018/3/7.
  */
class AppleActor extends Actor {
  override def act(): Unit = {
    while (true) {
      receive {
        case "start" => println("starting...")
        case SyncMsg(id, msg) => {
          println(id + ",sync " + msg)
          Thread.sleep(5000)
          sender ! ReplyMsg(3, "finished")
        }
        case AsyncMsg(id, msg) => {
          println(id + ",async " + msg)
          Thread.sleep(5000)
        }
      }
    }
  }
}

object AppleActor {
  def main(args: Array[String]): Unit = {
    val a = new AppleActor
    a.start()
    a ! AsyncMsg(1, "hello actor")
    println("异步消息发送完成")
    val reply = a !! SyncMsg(2, "hello actor")
    println(reply.isSet)
    val c = reply.apply()
    println(reply.isSet)
    println(c)
  }
}

case class SyncMsg(id: Int, msg: String)

case class AsyncMsg(id: Int, msg: String)

case class ReplyMsg(id: Int, msg: String)