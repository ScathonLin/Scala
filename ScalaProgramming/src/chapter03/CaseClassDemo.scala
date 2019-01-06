package chapter03

import scala.util.Random

/**
  * Created by linhd on 2018/3/7.
  */
case class SubmitTask(id: String, name: String)

case class HeartBeat(time: Long)

case object CheckTimeOutTask

object CaseClassDemo extends App {
  val arr = Array(CheckTimeOutTask, HeartBeat(12333), SubmitTask("0001", "task-0001"))
  arr(Random.nextInt(arr.length)) match {
    case SubmitTask(id, name) =>
      println(s"$id, $name")
    case HeartBeat(time) =>
      println(time)
    case CheckTimeOutTask =>
      println("check")
  }
}

