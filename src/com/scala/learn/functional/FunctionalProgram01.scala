package com.scala.learn.functional

/**
  * scala函数式编程（高阶函数）
  * scala中函数是头等公民，就和数字一样，可以在变量中存放函数
  */

import scala.math._

object FunctionalProgram01 {
  def main(args: Array[String]): Unit = {
    func08()
    //    func07()
    //    func05()
    //    func04()
    //    func03()
    //    func02()
    //    func01()
  }

  /**
    * 12.9、控制抽象
    * scala中可以将一系列语句归组成不带参数也没有返回值的函数，举例来说：
    * 如下函数在线程中执行某段代码：
    * def runInThread(block:()=>Unit){
    * new Thread{
    * override def run(){block()}
    * }.start()
    * }
    */
  def func08() {
    def runInThread(block: () => Unit) {
      new Thread {
        override def run() {
          block()
        }
      }.start()
    }
    //    runInThread(()=>println())
    runInThread {
      //如果不止一句代码，需要将括号换成大括号，
      () => println("HI!"); Thread.sleep(5000); println("Bye!")
    }
  }

  /**
    * 12.8、柯里化
    * 指的是将原来接受两个参数的函数变成新的接受一个参数的过程，
    * 新的函数返回一个以原有第二个参数作为参数的函数
    */
  def func07() {
    def mul(x: Double, y: Double): Double = x * y
    def mulAtOneTime(x: Double) = (y: Int) => x * y
    println(mul(6, 7))
    /*
     * 严格来讲，mulAtOneTime的结果是函数(y:Int)=>6*y ，而这个函数又被应用到7.因此最终结果是42
     */
    println(mulAtOneTime(6)(7))
    //scala支持如下简写来定义这样的柯里化函数
    def mulOneAtTime(x: Int)(y: Int) = x * y
    println(mulOneAtTime(6)(7))

    val a = Array("Hello", "World")
    val b = Array("Hello", "world")
    println(a.corresponds(b)(_.equalsIgnoreCase(_)))
    println(a.corresponds(b)((a, b) => a.equalsIgnoreCase(b)))
  }

  /**
    * 12.7、SAM转转换
    */
  def func06() {

  }

  /**
    * 12.5、一些有用的高阶函数
    */
  def func05() {
    val ran = (1 to 9).map(0.1 * _)
    println(ran)
    println("*" * 8)
    (1 to 9).map("*" * _).foreach(println _)
    println("===============")
    (1 to 9).map((x: Int) => "*" * x).foreach((s: String) => println(s))
    println("===============")
    (1 to 9).map(x => "*" * x).foreach(s => println(s))

    println("=======filter函数=======")
    //得到一个序列中的所有偶数，这不是最高效的方式
    (1 to 9).filter(_ % 2 == 0).foreach(println(_))
    /*
     * reduceLeft方法接受一个二元函数，即一个带有两个参数的函数---并将它应用到序列中的所有元素，从左到右，例如：
     */
    println("======reduceLeft函数======")
    /*
     * 1*2*3*4*5*6*7*8*9
     * 严格来说是这种计算形式：
     * (...((1*2)*3)*...*9)
     */
    println((1 to 9).reduceLeft(_ * _))
    println("======二元函数排序=======")
    val strArr = "Mary has a little lamb".split(" ").sortWith(_.length < _.length)
    println(strArr.mkString(","))
  }

  /**
    * 12.4、参数（类型）判断
    * def valueAtOneQuarter(f: (Double) => Double) = f(0.25)
    * 当将一个匿名函数传递给另一个函数或者方法的时候，
    * scala会尽可能帮助你推断出类型信息。例如，不需要将代码写成：
    * valueAtOneQuarter((x:Double) => 3 * x)
    * 由于valueAtOneQuarter方法指导你会传入一个类型为(Double)=>Double的函数，所以可以简单地
    * 写成valueAtOneQuarter((x)=>3 * x)
    * 只有一个参数可以省略参数外围括号
    * valueAtOneQuarter(x=>3 * x)
    * 若参数在=>右侧只出现一次，那么可以用“_”替换掉它
    * valueAtOneQuarter(3 * _),这已经是终极版本了。并且阅读起来很容易--一个将其值乘以3的函数
    */
  def func04() {
    //这里valueAtOneQuarter函数已经限制了传入的函数参数类型。
    def valueAtOneQuarter(f: (Double) => Double) = f(0.25)
    println(valueAtOneQuarter((x) => 3 * x))
    println(valueAtOneQuarter(x => 3 * x))
    println(valueAtOneQuarter(3 * _))

  }

  /**
    * 12.3、带函数参数的函数
    * (参数类型) => 结果类型
    * scala定义函数的两种方式：
    *
    * 函数类型后面定义
    * val func = (x:Int,y:Int)=>{function body}
    *
    * 函数的类型紧跟在变量后面
    * val func:(Int,Int)=>Long={(x,y)=>x+y}
    *
    */
  def func03() {
    def valueAtOneQuarter(f: (Double) => Double) = f(0.25)
    def f(x: Double): Double = {
      sqrt(x)
    }
    println(valueAtOneQuarter(f))
    println(valueAtOneQuarter(sqrt _))
    println(valueAtOneQuarter(ceil _))
    /*
     * valueAtOneQuarter的类型是：((Double)=>Double)=>Double
     * 由于valueAtOneQuarter是一个接收函数参数的函数，因此它被称为“高阶函数”
     * 高阶函数也可以产出另一个函数，以下是一个简单的实例
     */
    def mulBy(factor: Double) = (x: Double) => factor * x
    val newFun = mulBy(0.5) //返回一个函数
    println(newFun(2)) //执行高阶函数产生的新函数
    /*
     * mulBy(0.5)返回函数(x:Double)=>3*x，这个函数跟之前的形式差不多，但是mulBy的威力在于，
     * 它可以产出乘以任何数额的函数
     * mulBy的类型是:(Double)=>((x:Double)=>Double)
     */
  }

  /**
    * 12.2、匿名参数
    * scala中，不需要给每一个函数命名，正如你不需要给每个数字命名一样
    */
  def func02() {
    val fun = (x: Double) => 3 * x
    //相当于：def triple(x: Double) = 3 * x
    println(fun(3))
    //将Array中的每个元素×3
    val arr = Array(3.14, 1.42, 2.0).map((x: Double) => 3 * x)
    println(arr.mkString(" "))
  }

  /**
    * 12.1、作为值的参数
    */
  def func01() {
    /*
     * 从技术上讲，_将ceil方法变成了函数，scala中，无法直接操纵方法，而只能直接操作函数
     * 对函数做两件事：
     * 调用它，
     * 传递它，存放在变量中，或者作为参数传递给另一个函数
     */
    val num = -3.14
    val fun = ceil _
    //调用过程其实和函数调用过程一样，只不过唯一的区别就是，fun是一个包含函数的变量，而不是一个固定的函数
    println(fun(num))
    //将fun传递给另一个函数
    val arr = Array(3.14, 1.42, 2.0).map(fun) //Array(4.0,2.0,2.0)
    println(arr.mkString(" "))
  }

}