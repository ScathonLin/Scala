package com.scala.learn.actor

import java.io.File

import scala.actors.Actor
import scala.collection.mutable
import scala.io.Source

/**
  * Created by linhd on 2017/12/3.
  * scala.io.Source.fromFile("D:/data/word1.txt").getLines.map(_.split(" ")).flatten.map((_,1)).toList.groupBy(_._1).map(tuple=>(tuple._1,tuple._2.size))
  * scala.io.Source.fromFile("D:/data/word1.txt").getLines.flatMap(_.split(" ")).map((_,1)).toList.groupBy(_._1).map(
  * entry=>(entry._1,entry._2.size)).toList.sortBy(_._2).reverse
  */
object WordCount {
  def main(args: Array[String]) {
    val files = Array[String]("D:/data/word1.txt", "D:/data/word2.txt")
    val reduceActor = new TaskActor()
    reduceActor.start()
    for (file <- files) {
      val taskActor = new TaskActor()
      taskActor.start()
      val replay = taskActor !! new File(file)
      reduceActor !! replay.apply()
    }
    println(reduceActor.reduceMap)
  }
}

class TaskActor extends Actor {
  val reduceMap = new mutable.HashMap[String, Int]()

  override def act(): Unit = {
    loop {
      react {
        case MapActor(file) => {
          val mapResult = new mutable.HashMap[String, Int]()
          val lineIterator = Source.fromFile(file, "utf-8").getLines
          for (line <- lineIterator) {
            val words = line.split(" ")
            for (word <- words) {
              mapResult.put(word, mapResult.getOrElse(word, 0) + 1)
            }
          }
          sender ! mapResult
        }
        case ReduceActor(mapResult: mutable.HashMap[String, Int]) => {
          for (key <- mapResult.keys) reduceMap.put(key, reduceMap.getOrElse(key, 0) + mapResult.get(key).get)
        }
      }
    }
  }
}

case class MapActor(file: File)

case class ReduceActor(mapResult: mutable.HashMap[String, Int])