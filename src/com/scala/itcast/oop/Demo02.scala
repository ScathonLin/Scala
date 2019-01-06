package com.scala.itcast.oop

import java.io.{IOException, FileNotFoundException}

/**
  * Created by linhd on 2017/12/4.
  */
object Demo02 {
  println("主构造器执行")
  try {
    scala.io.Source.fromFile("")
  }
  catch {
    case e: FileNotFoundException => e.printStackTrace()
    case e: IOException => e.printStackTrace()
    case e: Exception => e.printStackTrace()
  } finally {
    println("finally execute")
  }

  def main(args: Array[String]) {
    singleton()
  }

  def singleton(): Unit = {
    object SingletonObject {

    }
    println(SingletonObject)
    println(SingletonObject)
  }
}

class Demo02 {

}

