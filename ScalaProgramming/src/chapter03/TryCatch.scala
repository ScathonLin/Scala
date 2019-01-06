package chapter03

import scala.io.Source
import scala.util.control.NonFatal

/**
  * Created by linhd on 2018/1/28.
  * 真琴りょう
  */
object TryCatch {
  def main(args: Array[String]) {
    val args = List("D:\\Coding\\Project\\IDEAProject\\Scala\\app.log", "D:\\Coding\\Project\\IDEAProject\\Scala\\number1.txt")
    args foreach (arg => countLines(arg))
  }

  def countLines(fileName: String) = {
    println()
    var source: Option[Source] = None
    try {
      source = Some(Source.fromFile(fileName))
      val size = source.get.getLines().size
      println(s"file $fileName has $size lines")

    } catch {
      case NonFatal(ex) => println(s"Non fatal exception!$ex")
    } finally {
      for (s <- source) {
        println(s"Closing $fileName...")
        s.close()
      }
    }
  }
}
