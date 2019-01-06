package com.scala.generic

/**
  * Created by linhd on 2017/12/26.
  */
class MissRight[T] {

   //  更强劲的代码，将Ordering转化成Ordered
   def select(first: T, second: T)(implicit ord: Ordering[T]): T = {
      import Ordered.orderingToOrdered
      //    if (ord.gt(first, second)) first else second
      if (first > second) first else second
   }
}

object MissRight {
   def main(args: Array[String]) {
      val mr = new MissRight[Girl]
      val g1 = new Girl("linhd", 88)
      val g2 = new Girl("shenxiaohan", 100)
      import MyPredef._
      val result2 = mr.select(g1, g2)
      println(result2.toString)
   }
}