package com.scala.learn.oop

/**
 * 面向对象编程
 */

object ObjectProgramming {
	def main(args: Array[String]): Unit = {
		beanProperty()
		//		objectPrivateField()
		//		getterAndSetter()
		//		func03()
		//		func02()
		//		func01()
	}

	/**
	 * Bean属性,生成符合javabean的getter以及setter
	 */
	def beanProperty() {
		/*
		 * 这个地方有个坑，低版本是scala.reflect.BeanProperty，不是scala.beans.BeanProperty
		 */
		import scala.beans.BeanProperty
		class Person {
			@BeanProperty()
			var name: String = "linhd"
		}
		/**
		 * 编译之后scala会生成四个方法：
		 * name:String
		 * name_=(newValue:String):Unit
		 * getName():String
		 * setName(newValue:String):Unit
		 *
		 * javap命令验证：
		 * $ javap -private Person
		 * Compiled from "Person.scala"
		 * public class Person {
		 * private java.lang.String name;
		 * public java.lang.String name();
		 * public void name_$eq(java.lang.String);
		 * public java.lang.String getName();
		 * public void setName(java.lang.String);
		 * public Person();
		 * }
		 *
		 */
		val person = new Person()
		println(person.name)
		person.setName("linxw")
		println(person.getName())
	}

	/**
	 * getter setter属性的使用
	 */
	def getterAndSetter() {
		class Class01 {
			val name = "linhd"
		}
		class Class02 {
			var name = "linxw"
		}
		val class01 = new Class01()
		/*
		 * 因为在Class01中name是不可变变量（val） 所以scala只会给name自动生成一个getter方法（其实就是相当于name属性）
		 * 但是没有setter方法（name_）因为变量不可变
		 * class01.name_=("test")//调用失败
		 *
		 * 通过javap命令进行验证
		 * $ javap -private Class01
		 * Compiled from "Class01.scala"
	   *	public class Class01 {
	   *	  private final java.lang.String name;
	   *	  public java.lang.String name();
	   *	  public Person();
	   *	}
		 *
		 *
		 */
		//		class01.name_=("test")
		println(class01.name)
		val class02 = new Class02()
		/*
		 * Class02类中的name可变，所以scala自动为其生成了getter and setter方法
		 * getter就是name属性
		 * setter就是name_
		 * scala中getter和setter称之为属性（property）
		 * 所以通过name_=(nameValue)可以设置name值
		 *
		 * 通过Javap命令进行验证
		 * $ javap -private Person
	   *	Compiled from "Person.scala"
	   *	public class Person {
	   *	  private java.lang.String name;
	   *	  public java.lang.String name();
	   *	  public void name_$eq(java.lang.String);
	   *	  public Person();
	   *	}
		 *
		 */
		class02.name_=("linhd")
		println(class02.name)
	}

	/**
	 * 对象私有字段的使用，和Java的有所区别
	 */
	def objectPrivateField() {

		//内部类
		class InnerClass {
			private var value = 0
			private[this] var notValue = scala.util.Random.nextInt(10)
			def current() = value
			def increment() {
				value += 1
			}
			/*
			 * 这里other:Counter可以访问到自己的私有属性value，这在Java中是不可以的，
			 * Java中的私有属性只能在本对象中访问，scala可以在对象之外访问，前提是Counter（针对本例而言）类型
			 */
			//			def isLess(other: InnerClass) = value < other.value 这种方式可以访问到私有属性value的值
			def isLess(other: InnerClass) = value < other.current()
			/*
			 * def isMore(other:InnerClass)=notValue<other.notValue()
			 * other此时访问不到自己的notValue字段，因为该字段现在被private[this]修饰，
			 * 说明只有在自己对象（是对象而不是类）内部才能访问到这个字段，而此时other访问该字段的时候
			 * 不是在自己的对象中，而是在自己归属的类的另一个对象实例中访问，所以访问不到该私有字段，
			 * 这种用法和Java中的private字段的性质就一样了，这种字段成为对象私有字段
			 */

			//			def isMore(other:InnerClass)=notValue<other.notValue()
			def visitPrivateField() = {
				println(notValue)
			}
		}
		val o1 = new InnerClass()
		val o2 = new InnerClass()
		o1.increment()
		o2.increment()
		o2.increment()
		println(o1.isLess(o2))
	}
	def func02(): Unit = {
		var person = new Person()
		person.age_=(10)
		person.age = 100
		println(person.age)
	}
	def func01(): Unit = {
		val myCounter = new Counter()
		myCounter.increment()
		/*
		 * 带不带括号都可以运行成功，但是通常规则如下：
		 * 对于改值器，调用的时候加上()
		 * 对于取值器，调用的时候不加
		 */
		println(myCounter.current)
		println(myCounter.current())
	}
}
class Person {
	private var privateAge = 0
	def age = privateAge
	//私有字段的setter方法
	def age_=(newValue: Int) {
		if (newValue > privateAge) privateAge = newValue
	}
}
/**
 * scala的类不声明为public，一个源文件可以包含多个类
 */
class Counter {
	private var value = 0
	//方法默认是共有的
	def increment(): Unit = {
		value += 1
	}
	def current() = value
}