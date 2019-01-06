package com.scala.learn.basic

import scala.util.control.Breaks._

object ConditionExpressionAndForLoop {
   def main(args: Array[String]): Unit = {
      val s = "linhd"
      println(s(2))
      func01()
      func02()
      func03()
      func04()
      func05()
      func06()
      func07()
      func08()
   }

   /**
     * scala中break的用法，scala中没有break直接使用方法，
     * 但是可以借助scala提供的方式实现
     */
   def func08() {
      breakable {
         for (i <- 1 to 10)
            if (i < 5) {
               println(i)
            } else {
               break
            }
      }
   }

   /**
     * for循环推导式
     */
   def func07(): Unit = {
      val result = for (i <- 1 to 10) yield i % 3
      //是一个集合Vector
      println(result)
      var result2 = for (i <- 0 to 1; c <- "hello") yield (c + i).toChar
      println(result2)

      /**
        * 将生成器，守卫和定义包含在花括号中，不用分好进行分隔，用换行符分隔就行了
        */
      result2 = for {
         i <- 0 to 1
         c <- "hello"
      } yield (c + i).toChar
      println(result2)
   }

   /**
     * 高级for循环03
     */
   def func06(): Unit = {
      for (i <- 1 to 3; from = 4 - i; j <- from to 3) print((10 * i + j) + " ")
      println()
   }

   /**
     * 高级for循环02
     * 带着守卫的生成式
     */
   def func05(): Unit = {
      for (i <- 1 to 3; j <- 1 to 3 if i != j) print((10 * i + j) + " ")
      println()
   }

   /**
     * 高级for循环01
     * 生成器（多个生成器），to是闭区间，util是左闭右开
     */
   def func04(): Unit = {
      for (i <- 1 to 3; j <- 1 to 3) print((10 * i + j) + " ")
      println()
   }

   /**
     * foreach
     */
   def func03(): Unit = {
      var sum = 0
      for (ch <- "Hello world") sum += ch
      println(sum)
   }

   /**
     * Range用法
     */
   def func02(): Unit = {
      val stringBuilder = new StringBuilder()
      for (i <- Range(1, 10)) {
         stringBuilder.append(i).append(" ")
      }
      println(stringBuilder.toString())
   }

   /**
     * 块表达式以及赋值
     */
   def func01(): Unit = {
      val x0 = 2
      val y0 = 3
      val x1 = 5
      val y1 = 7
      val distance = {
         val x = x0 - x1; val y = y0 - y1; scala.math.sqrt(x * x + y * y)
      }
      println(distance)
   }
}