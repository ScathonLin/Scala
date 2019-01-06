package chapter03

import scala.collection.mutable.ArrayBuffer

/**
  * Created by linhd on 2018/3/6.
  */


object ClazzDemo {
  def main(args: Array[String]): Unit = {
    val human = new Human()
    human.fly()
    println(human.fight())
  }
}

trait Flyable {
  def fly(): Unit = {
    println("I can fly")
  }

  def fight(): String
}

abstract class Animal {
  def run(): Int

  val name: String
}

class Human extends Animal with Flyable {
  val name = "abc"
  val t1, t2, (a, b, c) = {
    println("ABC")
    (1, 2, 3)
  }
  println(a)
  println(t1._1)

  override def fight(): String = {
    "fight with bangzi"
  }

  def run(): Int = {
    1
  }
}

object AppObject extends App {
  println("I Love You Scala")
  println("nihao")
}

class Dog {
  val id = 1
  private var name = "linhd"

  def printName(): Unit = {
    println(Dog.CONSTANT + name)
  }

}

object Dog {
  private val CONSTANT = "wangwangwang"

  def main(args: Array[String]): Unit = {
    val p = new Dog
    p.name = "123"
    p.printName()
  }
}

object SingletonDemo {
  def main(args: Array[String]): Unit = {
    val session = SessionFactory.getSession
    println(session)
  }
}

object SessionFactory {
  var counts = 5
  val sessions = new ArrayBuffer[Session]()
  while (counts > 0) {
    sessions += new Session
    counts -= 1
  }

  def getSession: Session = {
    sessions.remove(0)
  }
}

class Session {

}