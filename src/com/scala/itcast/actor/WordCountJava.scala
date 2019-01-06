package com.scala.itcast.actor

import java.io.{BufferedReader, FileInputStream, IOException, InputStreamReader}
import java.util.concurrent.{ConcurrentHashMap, Executors}

/**
  * Created by linhd on 2017/12/5.
  */
object WordCountJava {
  def main(args: Array[String]) {
    val fileNames = Array[String]("D:/data/word1.txt", "D:/data/word2.txt")
    val threadPool = Executors.newFixedThreadPool(5);
    //    fileNames.map(fileName => new Thread(new WordCountTask(fileName)).start())
    fileNames.map(fileName => threadPool.submit(new WordCountTask((fileName))))
    println(WordCountTask.resultMap)
  }
}

class WordCountTask(fileName: String) extends Runnable {

  override def run(): Unit = {
    println(Thread.currentThread().getName)
    var fileInputStream: FileInputStream = null
    var inputStreamReader: InputStreamReader = null
    var bufferedReader: BufferedReader = null
    try {
      fileInputStream = new FileInputStream(fileName)
      inputStreamReader = new InputStreamReader(fileInputStream)
      bufferedReader = new BufferedReader(inputStreamReader)
      var line: String = ""
      while ( {
        line = bufferedReader.readLine();
        line != null
      }) {
        val words = line.split(" ")
        val resultMap = WordCountTask.resultMap
        words.map(word => resultMap.put(word, resultMap.getOrDefault(word, 0) + 1))
      }
    } catch {
      case ex: IOException => ex.printStackTrace()
    } finally {
      if (bufferedReader != null) bufferedReader.close()
      if (inputStreamReader != null) inputStreamReader.close()
    }
  }
}

object WordCountTask {
  //  val resultMap = new mutable.HashMap[String, Int]()
  val resultMap = new ConcurrentHashMap[String, Int]()
}
