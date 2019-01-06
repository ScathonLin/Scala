package com.scala.generic

/**
  * Created by linhd on 2017/12/26.
  */


/*
上下文界定：ContextBound
必须传进·类型参数必须传进去一个隐式转换的值
 */
class Chooser[T: Ordering] {
  def choose(first: T, second: T): T = {
    //    这一句是关键
    val ord = implicitly[Ordering[T]]
    if (ord.gt(first, second)) first else second
    //    这种方式不行
    //    if (first gt second) first else second
  }
}

object Chooser {
  def main(args: Array[String]) {
    //    一定要在Girl new是实例之前导入隐式规则，因为在new的时候要发生转换操作，如果在new之后import，没卵用
    import MyPredef._
    val chooser = new Chooser[Girl]()
    val g1 = new Girl("linhd", 90)
    val g2 = new Girl("shenxiaohan", 100)
    //    ，如果不导入MyPredef，这样运行会出错，编译通不过，因为Girl没有定义如何进行比较
    //    println(chooser.choose(g1, g2))
    //    导入隐式上下文，
    println(chooser.choose(g1, g2))
  }
}