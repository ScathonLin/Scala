package com.scala.learn.collections

import scala.io.Source

/**
 * 集合部分学习
 * 1、Seq是一个有先后次序的值得序列，比如数组和列表。
 * IndexedSeq允许我们通过整形的下标快速地访问任意元素。比如ArrayBuffer是带下标的，但是链表不是
 * 2、Set是一组没有先后次序的值，在SortedSet中，元素以某种排过序的顺序被访问。
 * 3、Map是一组（键、值）对偶。SortedMap按照键的排序访问其中的实体
 */
object CollectionPart01 {
  def main(args: Array[String]): Unit = {
    func13()
    //    func12()
    //    func11()
    //    func10()
    //    func09()
    //    func08()
    //    func07()
    //    func06()
    //    func05()
    //    func04()
    //    func02()
    //    func01()
  }

  /**
   * 13.14、懒视图
   */
  def func14() {
    
  }

  /**
   * 13.13、流
   * 每次调用next都会改变迭代器的指向，“流”提供的是一个不可变的替代品。
   * 流是一个尾部被懒计算的不可变列表--也就是说，只有你需要的时候才会被计算
   */
  def func13() {
    def numsFrom(n: BigInt): Stream[BigInt] = n #:: numsFrom(n + 1)
    /*
     * #::操作符很像是列表的::操作符，只不过它构造出的是一个流，
     * 当调用val tenOrMore= numsFrom(10)
     * 的时候，得到的是一个被显示为Stream(10,?)的流对象，
     * 其尾部是未被求值的，如果你调用tenOrMore.tail.tail.tail
     * 得到的将会是Stream(13,?)
     */
    println(numsFrom(1).map(x => x * x))
    println(numsFrom(2).map(x => x * x))
    val squares = numsFrom(1).map(x => x * x)
    println(squares.take(5).force) //取出5个值，千万别直接调用squares.force
    
    val words = Source.fromFile("D:/Coding/Project/scalaide/Scala/words.txt").getLines().toStream
    println(words)
    //Stream(lintl zhangmeili linhuadong linxw dings , ?)
    println(words(2))
  }

  /**
   * 13.12 迭代器
   * 可以用Iterator方法从集合获取一个迭代器。这种做法不像Java和C++那样普遍，
   * 但是对于那些完整构造需要很大开销的集合而言会很有用，主要作用就是节省内存开销，需要多少读取多少
   * 迭代器相对于集合来说是一个“懒”的替代品，只有需要用的时候才会取元素。
   */
  def func12() {
    val set1 = Set(0, 1, 2, 3)
    var ite = set1.iterator
    while (ite.hasNext) {
      println(ite.next())
    }
    //必须重新获取迭代器，否则取不出元素，因为迭代器已经遍历到底了
    ite = set1.iterator
    for (elem <- ite) {
      println(elem)
    }
  }

  /**
   * 13.11、拉链操作
   * 将两个集合中的元素集合在一起
   * 如果其中一个集合比另一个集合短，那么结果中的对偶数量和较短的那个集合的元素数量相同
   */
  def func11() {
    val prices = List(5.0, 20.0, 9.95)
    val quantities = List(10, 2, 1)
    println(prices zip quantities)
    //    List[(Double, Int)] = List((5.0,10), (20.0,2), (9.95,1))
    println((prices zip quantities).map(p => p._1 * p._2))
    //计算价钱List(50.0, 40.0, 9.95)
    println((prices zip quantities).map(p => p._1 * p._2) sum)

    //集合元素数量不一致的情况
    println(List(5.0, 20.0, 9.95) zip List(10, 2))
    //    List((5.0,10), (20.0,2))
    println(List(5.0, 20.0, 9.95) zipAll (List(10, 2), 0.0, 1))

    //zipIndex
    println("Scala".zipWithIndex)

    println("Scala".zipWithIndex.max)
    //返回(l,3)，因为l在Scala五个字母中最大
    println("Scala".zipWithIndex.max._2)
    //返回具有最大编码值的下标，因为l最大，l对应的下标值是3
  }

  /**
   * 13.10、化简、折叠、扫描
   * map方法可以将一元函数应用到集合的所有元素
   * 此例演示的是将二元函数组合集合中的元素
   */
  def func10() {
    println(List(1, 7, 2, 9).reduceLeft(_ - _))
    //((1-7)-2)-9
    println(List(1, 7, 2, 9).reduceRight(_ - _))
    //1-(7-(2-9))
    println(List(1, 7, 2, 9).foldLeft(0)(_ - _))
    //0-1-7-2-9=-19
    println(List(1, 7, 2, 9).foldLeft(1)(_ - _))
    //1-1-7-2-9=-18

  }

  /**
   * 将函数映射到集合
   */
  def func09() {
    val names = List("Peter", "Paul", "Mary")
    println(names.map(_.toUpperCase))
    println(names.map((name: String) => name.toUpperCase()))
    println(names.map((name: String) => Vector(name.toUpperCase(), name.toLowerCase())))
    //List(Vector(PETER, peter), Vector(PAUL, paul), Vector(MARY, mary))
    println(names.flatMap((name: String) => Vector(name.toUpperCase(), name.toLowerCase())))
    //List(PETER, peter, PAUL, paul, MARY, mary)
  }

  /**
   * 13.8、常用方法
   */
  def func08() {
    val set1 = Set(0, 1, 2, 3, 4)
    println(set1.sum)
    println(set1.filter(_ % 2 == 0))
    println(set1.max)
    println(set1.min)
    println(set1.take(2))
    //...很多
  }

  /**
   * 13.7、用于添加或去除元素的操作符
   */
  def func07() {
    val lst1 = List(0, 1)
    /**
     * Sequence
     */
    println(lst1 :+ 2) //在尾部添加元素
    println(2 +: lst1) //在头部添加元素
    /**
     * Set Map
     */
    val set1 = Set(0, 1)
    val map1 = Map("name" -> "linhd", "age" -> 22)
    println(set1 + 2)
    println(map1 + ("sex" -> "male", "addr" -> "shandong")) //添加与给定集合元素类型一致的元素

    /**
     * Set，Map ArrayBuffer
     */
    val ab1 = scala.collection.mutable.ArrayBuffer[Int](0, 1, 2)
    val ab2 = scala.collection.mutable.ArrayBuffer[Int](1)
    println(set1 - 1)
    println(map1 - ("name", "age"))
    println(ab1 - (0, 1)) //去除某个元素或者某些元素
    val set2 = Set(1)

    println(ab1 -- ab2)
    println(set1 -- set2)
    println(ab1 -- set1) //移除在set1中同时存在set1以及set2中的元素，相当于求差集，两个算子可以不一种类型
    /**
     * Iterable
     */
    println(set1 ++ ab1)
    println(ab1 ++: set1) //两个计算元素不一定同一种集合，可以互相组合

    /**
     * List
     */
    val lst2 = List(2, 3)
    println(1 :: lst2) //向前追加元素或者给定的列表
    println(lst2 ::: lst1) //:++
    println(lst1 ::: lst2) //追加列表元素，相当于++:

    /**
     * Set
     * | & &~
     */

    /**
     * 可变集合
     */
    println(ab1 += 3) //0,1,2,3
    println(ab1 += (3, 4, 5)) //0,1,2,3,4,5
    println(ab1 ++= lst2) //0,1,2,3
    println(ab1 -= 2) //0,1
    println(ab1 -= (0, 1)) //2
    println(ab1 --= lst2) //0,1

    /**
     * ArrayBuffer
     */
    val ab3 = scala.collection.mutable.ArrayBuffer[Int](0, 1)
    println(2 +=: ab3) //ab2向前追加元素
    println(ab2 ++: ab3) //ab2向前追加集合
  }

  /**
   * 13.6、集
   * 集是不重复元素的集合，尝试将已有的元素加入没有效果
   * 和列表不同的是，集并不保留元素插入的顺序，缺省的情况下，集是以哈希集实现的，
   * 其元素根据hashCode方法的值进行组织。
   */
  def func06() {
    //不保存插入顺序
    val set = Set(1, 2, 3, 4, 5, 6) + 1
    println(set)
    for (elem <- set) println(elem)
    //保存插入顺序
    val seqSet = scala.collection.mutable.LinkedHashSet("Mo", "Tu", "We", "Th", "Fr");
    for (elem <- seqSet) println(elem)
    //对元素进行排序，采用红黑树进行实现的
    val sortedSet = scala.collection.immutable.SortedSet(7, 1, 2, 3, 4, 5, 6)
    println(sortedSet)

    val digits = Set(1, 7, 2, 9)
    println(digits contains 0)
    println(Set(1, 2) subsetOf digits)
    /*
     * 集合操作：
     * union，intersect，diff方法执行通常地集合操作，也可以写成：| & &~
     * 或者将联合union写成++，差异diff写成--
     */
    val s1 = Set(1, 7, 2, 9)
    val s2 = Set(2, 3, 5, 7)
    println(s1 union s2)
    println(s1 | s2)
    println(s1 ++ s2)
    println(s1 intersect s2)
    println(s1 & s2)
    println(s1 diff s2)
    println(s1 &~ s2)
    println(s1 -- s2)

  }

  /**
   * 13.5、可变列表
   * 可变的LinkedList和不可变的List相似，只不过你可以通过对elem引用赋值修改其头部，
   * 对next的引用赋值来修改其尾部
   */
  def func05() {
    //    去除序列中所有的负数
    val lst = scala.collection.mutable.LinkedList(1, -2, 7, -9)
    var cur = lst
    while (cur != Nil) {
      if (cur.elem < 0)
        cur.elem = 0
      cur = cur.next
    }
    println(lst)

    //去除序列中每两个元素之间的一个
    cur = lst
    while (cur != Nil && cur.next != Nil) {
      cur.next = cur.next.next;
      cur = cur.next
    }
    //cur其实一直是个LinkedList
    println(lst)
  }

  /**
   * 13.4、列表
   * Point1:
   * 	scala中，列表要么是Nil（即空表），要么是一个head元素加上一个tail，而tail又是一个列表。
   * Point2:
   * 	:: 操作符从给定的头和尾创建一个新的列表
   */
  def func04() {
    // Point1
    val digits = List(4, 2)
    println(digits.head)
    println(digits.tail)
    println(digits.tail.head)
    println(digits.tail.tail)
    // Point2，结合符是从右开始结合的，如果结尾不是List，那么要用Nil作为结尾才行
    val newList = 9 :: List(4, 2) :: 1 :: Nil
    println(newList)

    //遍历列表，计算列表元素之和
    def sum(list: List[Int]): Int = {
      if (list == Nil) 0 else list.head + sum(list.tail)
    }
    val list = 1 :: 2 :: 3 :: 4 :: Nil
    println(sum(list))
    println(list.tail.head) //相当于取出第2个元素
  }

  /**
   * 13.3、序列
   */
  def func03() {
    /**
     * 最重要的不可变序列：
     * ============================================
     * 								<<trait>>Seq
     * 											^
     * 											|
     * 			————————————————-----------------------------
     * 			|										|				|				|				|
     * <<trait>>IndexedSeq		List		Stream	Stack		Queue
     * 			^
     * 			|
     * ------------
     * |					|
     * Vector		Range
     *
     *
     * 最重要的可变序列：
     * ============================================
     * 								<<trait>>Seq
     * 											^
     * 											|
     * 			————————————————---------------------------------------------
     * 			|									|				|						|						|					|
     * <<trait>>IndexedSeq	Stack		Queue		PriorityQueue	LinkedList	DoubleLinkedList
     * 			^
     * 			|
     * ArrayBuffer
     *
     * Vector是ArrayBuffer的不可变版本：一个带下标的序列，支持快速的随机访问。向量是以树的结构形式实现的，
     * 每个节点不超过32个节点。
     */
  }

  /**
   * 13.2、可变集合与非可变集合
   * scala同时支持可变的和不可变的集合，不可变的集合从不改变，因此可以安全地共享引用，
   * 甚至是在一个多线程的应用程序中也没有问题。
   * scala优先采用不可变集合
   */
  def func02() {
    //计算出一个数字中所有出现的0-10的阿拉伯数字
    def digits(n: Int): Set[Int] = {
      if (n < 0) digits(-n)
      else if (n < 10) Set(n)
      else digits(n / 10) + (n % 10)
    }
    println(digits(15686))
  }

  def func01() {
    val map = Map({ "name" -> "linhd" }, { "age" -> 22 })
    println(map.get("name"))
    println(map("name"))
    println(map.get("age"))
    println(map("age"))
  }
}