package com.scala.itcast.grammer

/**
  * Created by linhd on 2017/12/3.
  */

object Grammer01 {
   def main(args: Array[String]) {
      func01()
   }


   def func02(): Unit = {
      val arr = Array[Int](10)
      println(arr.toBuffer)
      val lst = List(1, 2, 3)
      println(lst)
   }

   def func01(): Unit = {
      val iterator = for (i <- 1 to 3; j <- 1 to 3; if (i != j)) yield (i, j)
      println(iterator.filter(_._1 != 1))

      val func = (x: Int) => x * 2

      def m1(func: Int => Int): Unit = {
         println(1.to(10).map(func))
      }

      m1(func)
   }
}
