package com.scala.itcast.oop

/**
  * Created by linhd on 2017/12/4.
  */
class Chinese extends Human with Animal {
  //  override def run(): Unit = {
  //    println("chinese run...")
  //  }
}

object Chinese {
  def main(args: Array[String]) {
    val chinese = new Chinese
    chinese.run()
  }
}
