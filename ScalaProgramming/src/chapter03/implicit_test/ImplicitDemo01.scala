package chapter03.implicit_test

import java.io.File

import scala.io.Source

/**
  * Created by linhd on 2018/3/8.
  */
class RichFile(val from: File) {
  def read = Source.fromFile(from.getPath).mkString
}

object RichFile {
  implicit def file2RichFile(file: File) = new RichFile(file)
}

object ImplicitDemo01 {
  def main(args: Array[String]): Unit = {
    import RichFile._
    println(new File("D:\\Coding\\Project\\IDEAProject\\Scala\\ScalaProgramming\\src\\chapter03\\data\\test.dat").read)
  }
}