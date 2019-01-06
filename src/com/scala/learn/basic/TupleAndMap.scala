package com.scala.learn.basic
/**
 * 元组和映射
 */
object TupleAndMap {
	def main(args: Array[String]): Unit = {
		func06()
		//		func05()
		//		func04()
		//		func03()
		//		func02()
		//		func01()
	}

	/**
	 * 拉链操作
	 */
	def func06(): Unit = {
		val symbols = Array("<", "-", ">")
		val counts = Array(2, 10, 2)
		val pairs = symbols.zip(counts)
		for (pair <- pairs) println(pair)
		/**
		 * 输出结果
		 * (<,2)
		 * (-,10)
		 * (>,2)
		 */
		for ((s, n) <- pairs) Console.print(s * n)
		println()
		//将zip后的元组数组转成map
		val map = pairs.toMap
		println(map)
	}

	def func05(): Unit = {
		//声明方式一，注意元组中有n个元素，那么Tuple后面就是数字n，例如元组中有2个元素，那么声明的时候就是Tuple2(item1,item2)
		val t1 = Tuple3(1, 3.14, "Fred")
		println(t1._1)
		println(t1._2)
		println(t1._3)

		val t2 = (1, 3.14, "Fred")
		println(t2._1)
		//另外一种取值方式，注意：空格，没有连在一起
		println(t2 _2)

		val (f, s, t) = t2
		println((f, s, t))

		//可以指定那个元素没有用，用下划线代替
		val (first, second, _) = t2
		println(first, second)

		//根据规则对元组进行分组
		println("New York123".partition(_.isUpper)) //打印结果：(NY,ew ork)，大写的一组其余的一组
	}

	/**
	 * 与Java API的互操作
	 */
	def func04(): Unit = {
		//开发的时候不要把import写在这里
		import scala.collection.JavaConversions.mapAsScalaMap
		val scores: scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]
		scores.put("Linhd", 100)
		scores.put("Kobe", 20)
		println(scores)
		import scala.collection.JavaConversions.propertiesAsScalaMap
		val prop: scala.collection.Map[String, String] = System.getProperties()
		for ((k, v) <- prop) println(k + "->" + v)
	}

	/**
	 * 有序map
	 */
	def func03(): Unit = {
		val scores = scala.collection.immutable.SortedMap("Alice" -> 10, "Fred" -> 7, "Bob" -> 3, "Cindy" -> 8)
		println(scores)
	}

	/**
	 * map的迭代
	 */
	def func02(): Unit = {
		var scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
		//最简单的方式
		for ((k, v) <- scores) println(k + "-->" + v)
		for (v <- scores.values) print(v + " ")
		println()
		println(scores.keys)
		println(scores.keySet)
		for (k <- scores.keys) print(k + " ")
		println()
		for (k <- scores.keySet) print(k + " ")
		println()
		for (k <- scores.keySet) print(k + "->" + scores(k) + ",")
	}

	/**
	 * map的创建、更新、查询以及追加map
	 */
	def func01(): Unit = {
		//构建一个不可变映射
		var notVariableScores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
		//另一种定义方式
		notVariableScores = Map(("Alice" -> 10), ("Linhd" -> 100))
		//构建一个不可变映射
		val variableScores = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
		println(notVariableScores)

		//先判断map中是否有key对应的value，如果没有，返回指定的值,如果不检查，查询到了不存在的key的时候，那么会报错
		println(if (notVariableScores.contains("Bob")) notVariableScores("Bob") else 0)

		//另一种检查map中是否有查询的key的办法，使用getOrElse()方法
		println(notVariableScores.getOrElse("Bob", 0))

		//		println(notVariableScores.get("Bob").get)
		println(notVariableScores("Alice"))

		//测试不可变映射是否能追加映射集合
		notVariableScores += ("Linxw" -> 100)
		println(notVariableScores)
		notVariableScores -= "Linhd"
		println(notVariableScores)
		//notVariableScores += Map("Kobe" -> 20)这种方式不行

		//得到一个新的map
		println(notVariableScores + ("Kobe" -> 20, "Erwin" -> 25))

		println("*****************************")

		println(variableScores)

		//两种取值方式，一种是get但是取出的是Some(value)，需要再get一次才能拿出值
		println(variableScores.get("Bob"))
		println(variableScores.get("Bob").get)
		println(variableScores("Cindy"))

		variableScores.put("Cindy", 100) //可以设置值，不可变映射不可以进行映射
		variableScores("Cindy") = 250
		println(variableScores.get("Cindy"))

		//向原有映射中追加一个map映射集合
		variableScores += ("Bob" -> 10, "Fred" -> 7)
		println(variableScores)
		variableScores.++=(Map("Linhd" -> 100, "Linxw" -> 600))
		println(variableScores)

		//移除一个kv对
		variableScores.-=("Bob") //等价于variableScores -= "Bob"
		println(variableScores)
	}
}