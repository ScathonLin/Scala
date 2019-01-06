package com.scala.learn.oop.extend

import scala.beans.BeanProperty

/**
 * 继承
 */
object ExtendPoint {
	def main(args: Array[String]): Unit = {
		func03()
		//				func02()
		//		func01()
	}

	def func03() {
		val person = new Person("linhd", 22)
		val sub = new Employee("kobe", 22, 2000.0)
		person.parentMethod()
		sub.subClassMethod()
	}

	/**
	 * 类型检查与转换
	 */
	def func02() {
		val person = new Person("linhd", 22)
		val emp = new Employee("kobe", 22, 2000.0)
		//存在着向上兼容的特性，和Java一样
		println(person.isInstanceOf[Person]) //true
		println(emp.isInstanceOf[Person]) //true
		println(emp.isInstanceOf[Employee]) //true
		println(person.isInstanceOf[Employee]) //false

		println("===========================")
		//这种检查方法不存在向上兼容的特性，目的就是检查类到底是什么类
		println(person.getClass() == classOf[Person]) //true
		println(person.getClass() == classOf[Employee]) //false
		println(emp.getClass() == classOf[Person]) //false
		println(emp.getClass == classOf[Employee]) //true
	}

	def func01() {
		val person = new Person("linhd", 22)
		println(person.getName())
		//		person.setName("kobe")
		println(person.toString())
		val employee = new Employee("kobe", 22, 200.0)
		//		employee.setName("erwin")
		//		employee.setSalary(2000)
		println(employee.toString())
	}

}
class Person(@BeanProperty var name: String, @BeanProperty var age: Int) {
	//	@BeanProperty
	//	var name: String = "linhd"
	override def toString = getClass.getName + "[name=" + name + "],[age:" + age + "]"
	def parentMethod() = { println("method from parent class") }
}
/**
 * 注意点：
 * name，age都在父类中定义了，前面不能加var 或者 val，否则会报错，
 * 自己特有的新增字段要加上val或者var
 */
class Employee(name: String, age: Int, @BeanProperty var salary: Double) extends Person(name, age) {
	override def toString = super.toString() + ",[salary:" + salary + "]"
	def subClassMethod() = { println("method from sub class") }
}