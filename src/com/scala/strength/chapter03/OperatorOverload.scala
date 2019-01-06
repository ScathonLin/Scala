package com.scala.strength.chapter

import org.junit.Test

/**
  * 3.1 操作符重载
  */
@Test
class OperatorOverload {
   @Test
   def testFunc01(): Unit = {
      println(1 toString)
      println("hello" length)
      println(List(1, 2, 3) size)

      def isEven(num: Int) = num % 2 == 0

      List(1, 2, 3, 4).filter((num: Int) => isEven(num)).foreach((num: Int) => println(num))
      println("==========")
      List(1, 2, 3, 4).filter(num => isEven(num)).foreach(num => println(num))
      println("==========")
      List(1, 2, 3, 4).filter(isEven(_)).foreach(println(_))
      println("==========")
      List(1, 2, 3, 4).filter(isEven).foreach(println)
      println("====惊呆了，说话呢这是======")
      List(1, 2, 3, 4) filter isEven foreach println

      /**
        * 操作符重载的优先级规则（由低到高）
        *
        * 1. 所有字母
        * 2. |
        * 3. ^
        * 4. &
        * 5. < >
        * 6. = !
        * 7. :
        * 8. + -
        * 9. * / %
        * 10. 其他特殊字符
        *
        * Scala中有一个特殊的地方，与“运算符左结合”规则不同的是，任何名字以“:” 也就是冒号结尾的方法，都与右边的对象进行绑定.
        **/
   }

   /**
     * Scala中有一个特殊的地方，与“运算符左结合”规则不同的是，任何名字以“:” 也就是冒号结尾的方法，都与右边的对象进行绑定.
     */
   @Test
   def testFunc02(): Unit = {
      val list = List('b', 'c', 'd')
      // 冒号右结合规则
      println('a' :: list)
      // 使用. 转换成左结合规则
      println(list.::('a'))
   }
}
