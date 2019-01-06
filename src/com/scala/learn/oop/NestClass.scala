package com.scala.learn.oop

/**
 * 嵌套类
 */
object NestClass {
	def main(args: Array[String]): Unit = {
		nestClass()
	}
	def nestClass() {
		class Network {
			class Member(val name: String) {
				val contacts = name
			}
			private val members = new scala.collection.mutable.ArrayBuffer[Member]()
			def join(name: String) = {
				val m = new Member(name)
				members += m
				m
			}
			def membersInfo = members
		}
		val network = new Network()
		network.join("linhd")
		network.join("linxw")
		network.join("dings")
		new network.Member("linhd") //实例化内部类
		network.membersInfo += new network.Member("kobe")
		for (mem <- network.membersInfo) {
			println(mem.contacts)
		}
	}
}