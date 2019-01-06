package com.scala.learn.scalapackage

/*
 * 这个地方有一个坑，如果package文件与调用的类在同一个目录下面， 这样是可以的
 * 但是如果不在同一个目录下面，前面得跟上包名，然后加上package文件中定义的路径，例如
 * 引用Scala类的时候，由于Scala所在的package文件不和PackgePart.scala文件在同一个文件夹中
 * 所以得加上路径，Scala所在的package文件在package_test目录中，所以引用的时候格式如下：
 * import package_test.com.linhd.pro.Scala
 */

import com.linhd.pro.JavaPro
import com.linhd.pro.Python
import packageTest.com.linhd.pro.Scala
import _root_.com.scala.pkg

/**
  * 包的使用
  * scala中的包与Java中的包或者c++中的名称空间的目的是一样的：
  * 管理大型程序中的名称
  */
object PackagePart {
   def main(args: Array[String]): Unit = {
      val java = new JavaPro()
      val python = new Python()
      var scala = new Scala()
      var scalaHelloWorld = new com.linhd.scala.basic.HelloWorldDemo()
      println(com.name)
      println(pkg.name)
   }
}
