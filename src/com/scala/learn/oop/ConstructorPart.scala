package com.scala.learn.oop
/**
 * 构造器部分学习
 */
object ConstructorPart {
	def main(args: Array[String]): Unit = {
		secondConstructor()
		//		mainConstructor()
	}

	def secondConstructor() {
		class Person {
			private var name = ""
			private var age = 0

			def this(name: String) { //一个辅助构造器
				this()
				this.name = name
			}
			def this(name: String, age: Int) { //另一个辅助构造器
				this(name) //调用第一个辅助构造器
				this.age = age
			}
			def description = "name:" + this.name + ",age:" + this.age
		}
		var person = new Person("linhd")
		println(person.description)
		person = new Person("linhd", 22)
		println(person.description)
	}

	/**
	 * 主构造器
	 */
	def mainConstructor() {
		class Person(val name: String, val age: Int = 2, private val sex: String) {
			/**
			 * 使用javap命令查看生成的类的信息（java形式的）
			 * $ javap -private Person
			 * Compiled from "Person.scala"
			 * public class Person {
			 * private final java.lang.String name;
			 * private final int age;
			 * private java.lang.String sex;
			 * public java.lang.String name();
			 * public int age();
			 * private java.lang.String sex();
			 * private void sex_$eq(java.lang.String);
			 * public Person(java.lang.String, int, java.lang.String);
			 * }
			 *
			 * 1行代码完成了Java代码的6行的工作
			 * 这种直接在类名后面跟参数信息的就是主构造器，
			 * 如果类名后面没有参数，那么该类具备一个无参主构造器
			 */

			println("Just constructed anther person") //在主构造器中的代码，用于一些初始化工作
			def description = name + " is " + age + " years old, sex:" + sex
		}
		val person = new Person("linhd", sex = "m")
		println(person.name)
		println(person.age)
		//		println(person.sex) //无法调用，因为加了private
		println(person.description)
	}
}