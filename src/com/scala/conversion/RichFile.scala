package com.scala.conversion

import java.io.File

<<<<<<< HEAD
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


=======
import scala.io.Source


/**
  * Created by root on 2016/5/13.
  */
/*
一定要放在RichFile之前。否则会出错，因为如果将MyPredef放在后面的话，在加载RichFile的时候还没有加载MyPredef，所以失败
实际开发中，将隐式转换类单独放在一个文件中，然后在需要隐式转换的类文件的最开始加上import，将指定的类引进来就行了
 */
object MyPredef {
  implicit def fileToRichFile(f: File) = new RichFile(f)
}

class RichFile(val f: File) {
  def read() = Source.fromFile(f).mkString
}

object RichFile {
  def main(args: Array[String]) {
    val f = new File("D:/data/test.txt")
    //装饰 ，显示的增强
    //val contents = new RichFile(f).read()
    import MyPredef._
    val contents = f.read()
    println(contents)
  }
}

>>>>>>> ..
