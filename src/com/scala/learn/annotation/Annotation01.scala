package com.scala.learn.annotation

//import org.junit.Test

/**
 * 注解学习01
 * 用到再学吧
 */
object Annotation01 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 15.2、什么可以被注解
   * 类，方法，字段，局部变量，参数
   */
  def func02() {

  }

  /**
   * 15.1、什么是注解
   * java 注解并不影响编译器如何将源码翻译成字节码，它们仅仅是往字节码中添加数据，以便外部工具可以利用到它们。
   * 在scala中，注解可以影响编译过程，比如@BeanProperty注解触发getter和setter方法的生成
   */
  def func01() {
//    @Test(timeout = 100) def testSomeFeature() {}
  }
}