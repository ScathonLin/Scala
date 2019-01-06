package com.scala.learn.basic
/**
 * 异常处理部分
 *
 * 与Java一样，抛出的异常必须是Throwable的子类，
 * 但是scala没有受检异常，也就是说，不需要显式说明函数或者方法可能会跑出某种异常
 *
 */
object ExceptionPart {
	def main(args: Array[String]): Unit = {
		println("hello scala")
		func01(-9)
	}

	def func01(x: Int): Unit = {
		if (x >= 0) scala.math.sqrt(x) else throw new IllegalArgumentException("x should not be negative")
	}
}