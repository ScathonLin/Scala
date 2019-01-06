package com.scala.learn.oop.extend

import java.util.UUID

/**
 * 重写字段的演示+匿名子类
 */
object ExtendPoint2 {
	def main(args: Array[String]): Unit = {
		var student = new _Student()
		println(student.toString())
	}
}
abstract class _Person {
	def id: Int
	val name: String
	def age() = {
		-1
	}
	//	def format_addr(addr: String)
	var sex: String
}

/**
 * _Person编译完成后的字节码如下所示
 * $ javap -private Person
 * Compiled from "Person.scala"
 * public abstract class Person {
 * public abstract int id();
 * public abstract java.lang.String name();
 * public int age();
 * public abstract java.lang.String sex();
 * public abstract void sex_$eq(java.lang.String);
 * public Person();
 * }
 *
 */

class _Student extends _Person {
	/*
	 * 学生的ID通过构造器输入，重写了父类定义的id，也就是每个子类都有自己的生成子类id的方法
	 * 注意如下限制：
	 * def只能重写另一个def
	 * val只能重写另一个val或者不带参数的def
	 * var只能重写另一个抽象的var
	 */
	override val id = {
		scala.util.Random.nextInt(100)
	}
	override val name = {
		UUID.randomUUID().toString()
	}
	override val age = {
		scala.util.Random.nextInt(50)
	}
	//		override val format_addr,//这一句是错的，因为val只能重写另一个val或者不带参数的def
	override var sex = {
		if (scala.util.Random.nextInt(10) % 2 == 0)
			"m"
		else
			"f"
	}
	override def toString(): String = {
		return "Student:[id:" + id + ",name:" + name + ",age:" + age + ",sex:" + sex + "]"
	}
}