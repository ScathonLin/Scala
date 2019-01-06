package com.scala.learn.scalapackage

/*
 * 坑:
 * package object的名称要和包名的根名称的一致，例如本例子是com，如果名称不符，找不到变量
 */
package object com {
	var name = "123"

}
package com {
	package linhd {
		package pro {
			class JavaPro {
				//从包对象中取出变量值
				println("this is " + name)
			}
			class Python {
				println("this is python")
			}
		}
	}
}
//串联式包语句
package com.linhd.scala {
	package basic {
		class HelloWorldDemo {
			println("this is scala hello world")
		}
	}
}