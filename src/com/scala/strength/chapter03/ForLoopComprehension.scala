package com.scala.strength.chapter03

import org.junit.Test

/**
  * for循环推导式
  *
  * for 循环推导式的第一句代码必须是： 使用箭头符号执行抽取/迭代操作
  */
@Test
class ForLoopComprehension {
   /**
     * 生成器表达式
     */
   @Test
   def generatorExpression(): Unit = {
      for (i <- 1 to 10) println(i)
   }

   /**
     * 保护式
     */
   @Test
   def protectedExpression(): Unit = {
      val fruits = List("Apple", "Orange", "Strawberry", "WaterMelon")
      for (f <- fruits if f.contains("an"))
         println(f)
      //添加多个保护式
      for (f <- fruits
           if f.contains("an")
           if f.startsWith("Or"))
         println(f)
      for (f <- fruits
           if f.contains("an") && f.startsWith("Or"))
         println(f)
   }

   /**
     * yield用法
     */
   @Test
   def yielding(): Unit = {
      val fruits = List("Apple", "Orange", "Strawberry", "WaterMelon")
      val filterFruit = for {
         fruit <- fruits
         if fruit.contains("an")
      } yield fruit
      println(filterFruit)
   }

   @Test
   def fieldExtendAndValueDefined(): Unit = {
      val lst = List(Some("Red"), None, Some("Green"), None, Some("Blue"))
      for {
         color <- lst
         cVal <- color
         upcasedColor = cVal.toUpperCase()
      } println(upcasedColor)
      println("==========")
      for {
         Some(color) <- lst
         upcasedColor = color.toUpperCase()
      } println(upcasedColor)
   }
}
