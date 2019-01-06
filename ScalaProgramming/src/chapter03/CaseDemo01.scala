package chapter03

import scala.util.Random

/**
  * Created by linhd on 2018/3/7.
  */
object CaseDemo01 extends App {
  matchArrElement()
  matchBasicType()

  def matchBasicType(): Unit = {
    val array = Array("hello", 1, 2.0, CaseDemo01)
    val v = array(Random.nextInt(4))
    println(v)
    v match {
      case x: Int => println("Int " + x)
      case y: Double if y >= 0 => println("Double " + y)
      case z: String => println("String " + z)
      case _ => throw new Exception("not match exception")
    }
  }

  def matchArrElement(): Unit = {
    val arr = Array("linhd", "linxw", "dingsong")
    val name = arr(Random.nextInt(arr.length))
    name match {
      case "linhd" => println("linhd")
      case "linxw" => println("linxw")
      case "dingsong" => println("dingsong")
      case _ => println("unknow person")
    }
  }
}

