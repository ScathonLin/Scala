package com.scala.itcast.oop

/**
  * Created by linhd on 2017/12/5.
  */
object TemplateObject extends App {

  case class SubmitTask(id: Int, name: String)

  case object CheckAlive

  val arr = Array(CheckAlive, SubmitTask(1, "task1"))
  arr(scala.util.Random.nextInt(arr.length)) match {
    case SubmitTask(id, name) => println(s"taskInfo[id:$id,name:$name]")
    case CheckAlive => println("this is checkalive")
    case _ => "unknow"
  }

}
