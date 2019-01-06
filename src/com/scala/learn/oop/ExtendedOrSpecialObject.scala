package com.scala.learn.oop
/**
 * 扩展类或者特质的对象以及apply方法的使用
 * 一个object可以扩展类以及一个或多个特质，其结果是一个扩展了指定类以及特质的类的对象，
 * 同时拥有在对象定义中给出的所有的特性，像是抽象类
 */
object ExtendedOrSpecialObject {
	def main(args: Array[String]): Unit = {
		//apply方法的调用来来创建对象实例
		var a1 = Account(12.0)
		println(a1.id)
		var a2 = Account(22.0)
		println(a2.id)
		var a3 = Account(32.0)
		println(a3.id)
		//扩展类或者特质的对象
		//		DonothingAction.redo()
		//		DonothingAction.undo()

	}
	abstract class UndoableAction(val description: String) {
		def undo(): Unit = {}
		def redo(): Unit = {}
	}
	object DonothingAction extends UndoableAction("Do nothing") {
		override def undo() { println("DonothingAction undo...") }
		override def redo() { println("DonothingAction redo...") }
	}

	/**
	 * 利用伴生对象实现apply方法，省去创建实例对象的时候需要new的操作
	 */
	class Account private (val id: Int, initialBalance: Double) {
		private var balance = initialBalance
	}
	object Account {
		private[this] var id: Int = 0
		def newUniqueNumber(): Int = {
			id += 1
			id
		}
		/*
		 * 在伴生对象中定义apply方法，这样创建实例的时候直接用类名（对象名）Account(arg1,arg2,arg3...)
		 *
		 */
		def apply(initialBalance: Double) = new Account(newUniqueNumber(), initialBalance)
	}
}