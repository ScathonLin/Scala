package com.scala.learn.basic

import java.text.MessageFormat

/**
 * Point：函数的使用
 */
object Function {
	def main(args: Array[String]): Unit = {
		func01(5)
		println(func02(5))
		println(func03(5))
		println(decorate(str = "linhd"))
		println(decorate(str = "linhd", left = "(", right = ")"))
		variableArgFunc(1, 2, 3, 4)
		/**
		 * 变长参数函数调用的一个特殊的地方，
		 * 对于这个函数调用的时候，如果只传入一个参数，那么这个参数必须是指定的类型
		 * （与定义函数的时候指名的类型相匹配的类型，此例中是Int类型），但是所以
		 * 这样调用是错误的，应该这样使用，val s = variableArgFunc(1 to 5:_*),
		 * 这样指明了将1 to 5 当做一个序列进行处理，而不是一个参数进行处理
		 * variableArgFunc(1 to 10)
		 */
		variableArgFunc(1 to 5: _*)
		println(variableArgFuncRecursive(1 to 5: _*))
		
		func04()
		func05();
	}
	
	/**
	 * 懒加载变量（懒值）
	 * 只有第一次使用的时候它才会加载，初始化被延迟
	 * 这种语法对于开销较大的初始化语句非常有用
	 */
	def func05():Unit={
		lazy val words = scala.io.Source.fromFile("./words.txt").mkString.split(" ")
		for(word <- words) print(word+" ")
	}
	
	/**
	 * 字符串格式化函数
	 */
	def func04():Unit={
		var str=MessageFormat.format("The answer is {0} is {1}", "everything",42.asInstanceOf[AnyRef])
		println(str)
	}

	/**
	 * args.head代表不定长参数序列中的第一个值，
	 * args.tail代表不定长参数中除去第一个元素剩下的元素序列
	 */
	def variableArgFuncRecursive(args: Int*): Int = {
		if (args.length == 0) 0 else args.head + variableArgFuncRecursive(args.tail: _*)
	}

	/**
	 * 变长参数的使用（非递归）
	 */
	def variableArgFunc(args: Int*): Unit = {
		var result = 0
		for (arg <- args) result += arg
		println(result)
	}

	/**
	 * 调用该函数的时候，要针对参数名字进行传值，例如，本例中调用的时候应该指明str="linhd"，
	 * 如果不显式指明left或者right，那么函数执行的时候就用默认值进行执行，命名参数调用的时候不用按照顺序调用，
	 * 只要指定了名称就可以了
	 */
	def decorate(str: String, left: String = "[", right: String = "]"): String = {
		left + str + right
	}

	/**
	 * 不带返回值，这种函数叫做过程，调用它只是为了起到它的副作用
	 */
	def func01(n: Int): Unit = {
		var r = 1
		for (i <- 1 to n) r *= i
		println("func01:" + r)
	}

	/**
	 * 带返回值
	 */
	def func02(n: Int): Int = {
		var r: Int = 1
		for (i <- 1 to n) r *= i
		return r
	}

	/**
	 * 递归实现
	 */
	def func03(n: Int): Int = {
		return if (n == 1) 1 else n * func03(n - 1)
	}

}