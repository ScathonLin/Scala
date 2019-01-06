package com.scala.learn.oop
//引入枚举对象，这样直接调用枚举就行了，不用加前缀
import Enum._
object Enum2 extends App {
	if (args(0)==Red.toString())
		println("stop")
	else if (args(0) == Yellow.toString())
		println("Resume")
	else if (args(0) == Green.toString())
		println("pass")
}