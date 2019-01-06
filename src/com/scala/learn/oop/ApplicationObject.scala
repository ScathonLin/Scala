package com.scala.learn.oop

/**
 * 应用程序对象的使用
 */
object ApplicationObject extends App {
	println("Hello World")
	if (args.length > 0)
		println("Hello:" + args(0))
	else
		println("Hello:" + args(1))
}
