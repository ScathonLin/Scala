package com.scala.learn.oop
/**
 * 枚举类的使用,
 * 实例代码中一共涉及2个自定义对象，还有一个是Enum2对象
 */
object Enum extends Enumeration {
	val Red = Value(0, "Red")
	val Yellow = Value(10, "Yellow")
	val Green = Value("Green")
	def main(args: Array[String]): Unit = {
		//如果不指定id，那么id为前面的的id+1
		println(Enum.Red)
		println(Enum.Red.id)
		println(Enum.Yellow)
		println(Enum.Yellow.id)
		println(Enum.Green)
		println(Enum.Green.id)
		println(Enum.withName("Red"))
		println(Enum(0))
	}
}