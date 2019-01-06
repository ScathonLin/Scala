package com.scala.generic

/**
  * Created by linhd on 2017/12/26.
  */
//这是一个上界，传进去的必须是一个Comparable[T]的一个子类型
class Pair[T <: Comparable[T]] {
  def bigger(first: T, second: T) = {
    if (first.compareTo(second) < 0) first else second
  }
}

class Person(val id: Int, val name: String) extends Comparable[Person] {

  override def toString: String = s"id:[id:${id},name:${name}]"

  override def compareTo(o: Person): Int = this.id - o.id
}

object Pair {
  def main(args: Array[String]) {
    val pair = new Pair[Person]()
    println(pair.bigger(new Person(1, "linhd"), new Person(2, "shenxiaohan")))
    //    用Int不行，因为Int并没有继承Comparble[T]接口，但是Integer继承了该接口
    val pair1 = new Pair[Integer]()
    println(pair1.bigger(1, 2))
  }
}