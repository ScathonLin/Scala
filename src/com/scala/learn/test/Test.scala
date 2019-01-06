package com.scala.learn.test

import java.io.File

import scala.io.Source

/**
  * Created by linhd on 2017/12/3.
  */
object Test {
   def main(args: Array[String]) {
      func01()
   }

   def func01(): Unit = {
      val wordIterator = Source.fromFile(new File("D:/data/word1.txt")).getLines()
         .flatMap(_.split(" ")).map((_, 1)).toList.groupBy(_._1).mapValues(_.size)
      for (item <- wordIterator) println(item)
   }
}
