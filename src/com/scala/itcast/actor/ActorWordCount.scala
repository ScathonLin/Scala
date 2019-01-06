package com.scala.itcast.actor

import java.io.File

import scala.actors.Actor
import scala.collection.{immutable, mutable}
import scala.collection.mutable.ArrayBuffer
import scala.actors.Future


/**
  * Created by linhd on 2017/12/5.
  */
object ActorWordCount {
  def main(args: Array[String]) {
    val fileNames = Array[String]("D:/data/word1.txt", "D:/data/word2.txt")
    val futureSet = new mutable.HashSet[Future[Any]]()
    for (fileName <- fileNames) {
      val actor = new SubmitTask
      actor.start()
      val reply = actor !! MapTask(fileName)
      futureSet.add(reply)
    }
    val resultMap = new mutable.HashMap[String, Int]()
    while (futureSet.size > 0) {
      val doneFuture = futureSet.filter(_.isSet)
      for (future <- doneFuture) {
        val result = future.apply().asInstanceOf[ReduceTask].mapTask
        result.map(entry => resultMap.put(entry._1, resultMap.getOrElse(entry._1, 0) + entry._2))
        futureSet.-=(future)
      }
    }

    println(resultMap)
  }

  class SubmitTask extends Actor {

    override def act(): Unit = {
      loop {
        react {
          case MapTask(fileName) => {
            sender ! ReduceTask(scala.io.Source.fromFile(new File(fileName)).getLines().flatMap(_.split(" ")).map((_, 1))
                    .toList.groupBy(_._1).mapValues(_.size))
          }
          case StopTask => exit()
        }
      }
    }
  }

  case class MapTask(fileName: String)

  case class ReduceTask(mapTask: Map[String, Int])

  case object StopTask

}