package chapter03

/**
  * Created by linhd on 2018/3/7.
  */
object PartialFuncDemo extends App {
  def func1: PartialFunction[String, Int] = {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  def func2(num: String): Int = num match {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  println(func1("one"))
  println(func1("two"))
}
