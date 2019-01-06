package com.scala.learn.oop

/**
 * 单例对象
 * scala没有静态方法或者静态字段，可以用object这个语法达到同样的目的
 * 对象定义了某个类的单个实例，包含了你想要的特性。
 * scala中可以用对象来实现如下功能：
 * 作为存放工具函数或者变量的地方；
 * 高效地共享单个不可变的实例；
 * 需要用单个实例来协调某个服务的时候；
 */
object Singleton {
	private var lastNumber = 0
	def newUniqueNumber() = {
		lastNumber += 1;
		lastNumber
	}
	def main(args: Array[String]): Unit = {
		/*
		 * 对象的构造器在第一次被使用的时候被调用，也就是在Singleton.newUniqueNUmber()
		 * 首次调用的时候被触发，如果一个对象从未被使用，那么其构造器也不会执行
		 */
		println(Singleton.newUniqueNumber())
		println(Singleton.newUniqueNumber())
		println(Singleton.newUniqueNumber())
	}
}