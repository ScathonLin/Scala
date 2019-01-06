package com.scala.itcast.oop

/**
  * Created by linhd on 2017/12/5.
  */
object OptionDemo extends App {
  val map = Map("a" -> 1, "b" -> 2)
  val res = map.get("c")
  val v = res match {
    case Some(i) => i
    case None => 0
  }
  println(v)


  //  偏函数
  def func01(num: String) = num match {
    case "name" => "linhd"
    case "age" => 22
    case _ => "unknown"
  }

  //定义一个方法，返回值是偏函数
  def func02(): PartialFunction[String, Int] = {
    case "one" =>
      println("one case")
      1
    case "two" => 2
    case _ => -1
  }


  // 定义了一个变量，该变量是偏函数类型
  def func03: PartialFunction[String, Int] = {
    case "one" =>
      println("one case")
      1
    case "two" => 2
    case _ => -1
  }

  println(func01("age"))
  val parFunc = func02()
  println(parFunc("two"))
  println(func03("two"))
}
