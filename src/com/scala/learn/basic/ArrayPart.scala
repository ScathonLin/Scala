package com.scala.learn.basic

/**
 * 数组部分
 */
object ArrayPart {
	def main(args: Array[String]): Unit = {
		matrixArray()
		//		sortFunction()
		//		remoteFirstNegativeButNotFirstInArray()
		//		func04()
		//		func03()
		//		func02()
		//		func01()
	}

	def matrixArray(): Unit = {
		//创建规则的二维矩阵或者二维数组
		val matrix = Array.ofDim[Int](3, 4)
		val random = new scala.util.Random()
		for (i <- 0 until matrix.length; j <- 0 until matrix(i).length) matrix(i)(j) = random.nextInt(20)
		for (i <- 0 until matrix.length) {
			for (j <- 0 until matrix(i).length) {
				print(matrix(i)(j) + "\t")
			}
			println()
		}

		println("*******************************")
		
		//创建不规则长度的数组
		val matrix2 = new Array[Array[Int]](10)
		for (i <- 0 until matrix2.length) matrix2(i) = new Array[Int](i + 1)
		//进行赋值
		for(i<-0 until matrix2.length;j<- 0 until matrix2(i).length) matrix2(i)(j)=random.nextInt(30)
			for (i <- 0 until matrix2.length) {
			for (j <- 0 until matrix2(i).length) {
				print(matrix2(i)(j) + "\t")
			}
			println()
		}
	}

	/**
	 * 排序以及几个函数
	 */
	def sortFunction(): Unit = {
		val arr = Array(2, 1, 6, 4, 5, 7, 9, 8)
		println(arr.sum)
		println(arr.max)
		println(arr.sorted)
		val a = new scala.collection.mutable.ArrayBuffer[Int]()
		a.++=(arr)
		println(a.sorted.reverse)

		val arr2 = Array(1, 4, 2, 6)
		scala.util.Sorting.quickSort(arr2)
		println(arr2.mkString(" and "))
	}

	/**
	 * 一个demo
	 * 除去数组或者序列中除了第一个负数之后的所有的负数.
	 * method1:一边遍历，一遍进行判断并进行remove，但是这种方式很低效，因为移除一个就要进行数组元素的移位操作
	 * method2:先找出不被移除的元素的index集合，然后将元素对应到数组中移除元素之后的位置，最后调用trimEnd()方法
	 * 主要思想，集合trimEnd()方法
	 */
	def remoteFirstNegativeButNotFirstInArray(): Unit = {
		var isFirst = true
		var arr = new scala.collection.mutable.ArrayBuffer[Int]();
		arr.++=(Array(1, 2, -3, 4, -5, -6, 7, 8, 9))
		println(arr)
		val indexes = for (i <- 0 until arr.length if isFirst || arr(i) > 0) yield {
			if (arr(i) < 0) isFirst = false; i
		}
		println(indexes)
		for (i <- 0 until indexes.length) arr(i) = arr(indexes(i))
		arr.trimEnd(arr.length - indexes.length)
		println(arr)
	}

	/**
	 * 数组转换，使用yield每次迭代对应一个，其实和Python中的yield差不多，减少内存占用
	 */
	def func04(): Unit = {
		val a = Array(1, 2, 3, 4, 5)
		var result = for (elem <- a) yield 2 * elem
		for (item <- result) print(item + " ")
		println()

		//函数式编程表示方法
		result = a.filter(_ % 2 == 0).map(2 * _)
		for (item <- result) print(item + " ")
		println()

		result = a.filter { _ % 2 == 0 } map { 2 * _ }
		for (item <- result) print(item + " ")
	}

	def func03(): Unit = {
		for (i <- 1 until 10) print(i + " ")
		println()
		//隔2个取1个
		for (i <- 1 until (10, 2)) print(i + " ")
		println()
		// 将区间反转之后将元素取出
		for (i <- (1 until 10).reverse) print(i + " ")
		println()
		val a = Array(1, 2, 3, 4, 5, 6)
		for (item <- a) print(item + " ")
		println()
	}

	/**
	 * 变长数组以及一些api的使用
	 */
	def func02(): Unit = {
		val b = new scala.collection.mutable.ArrayBuffer[Int]()
		//在尾部添加一个元素 1
		b.+=(1)
		println(b)
		//在尾部添加多个元素
		b.+=(2, 3, 4, 5, 6)
		println(b)
		//在尾部追加一个集合
		b.++=(Array(7, 8, 9)) //或者b++=Array(7,8,9)
		println(b)
		//在尾部移除最后n个元素,在尾部进行删除增加元素是一个高效的操作
		b.trimEnd(5)
		println(b)
		//在指定索引处添加元素，涉及到元素的平移，所以不是高效的操作
		b.insert(2, 6)
		println(b)
		//在指定索引处插入一个集合
		b.insertAll(2, Array(4, 5))
		println(b)
		//移除指定索引处的元素
		b.remove(2)
		println(b)
		//移除从指定索引处开始的n的元素
		b.remove(2, 2)
		println(b)

	}

	/**
	 * 数组的初始化赋值以及字符串数组的使用
	 */
	def func01(): Unit = {
		val nums = new Array[Int](10)
		for (i <- 0 until nums.length) nums(i) = new scala.util.Random().nextInt(10);
		val s = Array("Hello", "World")
		println(nums(0))
		println(s(1))
		for (num <- nums) print(num + " ")
		println()
		for (word <- s) print(word + " ")
		println()
	}
}