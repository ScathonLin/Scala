package com.scala.itcast.oop

/**
  * Created by linhd on 2017/12/4.
  */

private class Person {
  val id = "123"
  var name = "linhd"
  private var gender: String = "m"
  private[this] var addr: String = "shandong"

  private def printString(): Unit = {
    println("printString")
    println(Person.f1)
  }
}

object Person {
  private val f1 = "shenxiaohan"

  def main(args: Array[String]) {
    val person = new Person()
    println(person.id)
    println(person.name)
    //    私有字段可以在伴生对象中访问
    println(person.gender)
    //    println(person.addr) // 错误，访问不到
    person.printString()
  }
}

