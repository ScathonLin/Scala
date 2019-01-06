package com.scala

package object pkg {
	val name = "linhd"
}
package pkg {
	class Inner {
		println("hello " + name)
	}
}