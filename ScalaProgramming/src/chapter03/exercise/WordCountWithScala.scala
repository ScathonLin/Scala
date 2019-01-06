package chapter03.exercise

import scala.actors.{Actor, Future}
import scala.collection.mutable
import scala.io.Source

/**
  * Created by linhd on 2018/3/8.
  */
case class MapTask(path: String)

class WordCountWithScala extends Actor {
  override def act(): Unit = {
    loop {
      react {
        case MapTask(path: String) => {
          sender ! executeMapTask(path)
        }
      }
    }
  }

  def executeMapTask(path: String): mutable.Map[String, Int] = {
    val wordMap = mutable.Map[String, Int]()
    //    val addWordToMap = (word: String) => {
    //      wordMap += ((word, wordMap.getOrElse(word, 0) + 1))
    //    }
    //    Source.fromFile(path).getLines().flatMap(_.split(" ")).map((word: String) => addWordToMap(word))
    for (word <- Source.fromFile(path).getLines().flatMap(_.split(" "))) {
      wordMap += ((word, wordMap.getOrElse(word, 0) + 1))
    }
    wordMap
  }

}

object WordCountWithScala {

  def main(args: Array[String]): Unit = {
    val a = new WordCountWithScala()
    a.start()
    val executeResultCollection = mutable.HashSet[Future[Any]]()
    for (task <- args) {
      executeResultCollection.add(a !! MapTask(task))
    }
    val resultMap = mutable.HashMap[String, Int]()
    while (executeResultCollection.nonEmpty) {
      val toCompute = executeResultCollection.filter(_.isSet)
      for (r <- toCompute) {
        val result = r.apply().asInstanceOf[mutable.Map[String, Int]]
        for ((k, v) <- result) {
          resultMap.put(k, resultMap.getOrElse(k, 0) + v)
        }
        executeResultCollection.remove(r)
      }
    }
    println(resultMap)
  }
}

