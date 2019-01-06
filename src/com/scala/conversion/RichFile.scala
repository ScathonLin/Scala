package com.scala.conversion

import java.io.File

/**
  * Created by linhd on 2017/12/26.
  */
object ImplicitRichFile {
   /*
     看见是file类型的参数，“偷偷”地将file对象转化成了RichFile对象（其实感觉并不是将对象进行了转化，只是当调用增强方法的时候，file
     找不到这个方法，所以从隐式类中查找相应的增强转换， 本例中，找到了RichFile的read方法，）
      */
   implicit def file2RichFile(file: File) = new RichFile(file)
}

object RichFile {
   def main(args: Array[String]) {
      import ImplicitRichFile._
      val file: File = new File("F:\\test\\dex")
      println(file.read())
   }
}

/*
其实就是相当于包装器，将功能有所欠缺或者不方便的对象的功能进行进一步的强化，
只不过scala通过隐式转换，在不用改变原有的调用方式的情况下，“神秘”的将功能强化了
 */

class RichFile(file: File) {
   def read(): String = {
      return "this is file content read by richfile read"
   }
}
