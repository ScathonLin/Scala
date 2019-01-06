package com.scala.learn.identifier

/**
  * 标识符学习01
  * 变量、函数、类等名称统称为标识符。在scala中，在选择标识符的时候相比Java
  * 有更多的选择，可以遵循经典的模式：字母和数字字符的序列，以字母或者下划线开头，比如fred12或者_Wilma
  */
object IdentifierPart01 {
   def main(args: Array[String]): Unit = {
      func05()
      //    func02()
      //    func01()
   }

   /**
     * 结合性
     * 当有一系列相同优先级的操作符的时候，操作符的结合性决定了他们是从左到右还是从右到左求值
     * scala中所有操作符都是左结合的，除了：
     * · 以冒号（:）结尾的操作符
     * · 赋值操作符。
     * 尤其值得一提的是：用于构造列表的::操作是右结合的，例如
     * 1::2::Nil
     * 的意思是：
     * 1::(2::Nil)
     */
   def func05() {
      class Fraction(var n: Double, var d: Double) {
         def calculate(): Double = n / d
      }
      //伴生对象
      object Fraction {
         def apply(n: Int, d: Int) = new Fraction(n, d)

         def unapply(input: Fraction) =
            if (input.d == 0) None else Some((input.n, input.d))
      }
      val f = Fraction(3, 5)
      println(f.calculate())

   }

   /**
     * 优先级
     * 如果一次性使用两个或更多的操作符，又没给出括号的话，那么首先执行的是高优先级的操作符，举例来说：
     * 1 + 2 * 3 = 1 + 6 = 7
     * 除了赋值操作符外，优先级由操作符的首字符决定
     * 优先级顺序：
     * ==================================
     * ||最高优先级：除以下字符外的操作符字符||
     * ----------------------------------
     * || * / %												 ||
     * ----------------------------------
     * || + -													 ||
     * ----------------------------------
     * || :														 ||
     * ----------------------------------
     * || < >													 ||
     * ----------------------------------
     * || !=													 ||
     * ----------------------------------
     * || &														 ||
     * ----------------------------------
     * || ^														 ||
     * ----------------------------------
     * || |													 	 ||
     * ----------------------------------
     * ||	非操作符											 ||
     * ----------------------------------
     * ||	最低优先级：赋值操作符					 ||
     * ==================================
     * 此外：后置操作符的优先级低于中置操作符
     * a 中置操作符 b 后置操作符
     * 上述代码等同于
     * （a 中置操作符 b） 后置操作符
     **/
   def func04() {

   }

   /**
     * 赋值操作符
     * a 操作符=b
     * 等同于
     * a = a 操作符 b
     */
   def func03() {
   }

   /**
     * a 标识符
     * 中置操作符有两个参数，因此是二元的，
     * 只有一个参数的是一元操作符，
     * 1、如果出现在参数之后，就是一个后置操作符
     * 1 toString
     * 2、如果出现在参数之前，就称之为前置标识符
     * -a
     * + - ！ ~ 都是前置操作符
     */
   def func02() {
      println(1 toString)
      println(1.toString())
   }

   /**
     * 中置操作符
     * a 标识符 b
     */
   def func01() {
      val stringBuilder = new StringBuilder()
      for (i <- 1.to(10)) stringBuilder.append(i + ",")
      println(stringBuilder.toString())
      stringBuilder.clear()
      for (i <- 1 to 10) stringBuilder.append(i + ",")
      println(stringBuilder.toString())
      stringBuilder.clear()
   }
}