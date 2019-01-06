package chapter03

import scala.collection.mutable

/**
  * Created by linhd on 2018/3/9.
  */
object GrammerDemo {
  def main(args: Array[String]): Unit = {
    val map = mutable.HashMap[String, Any]()
    map += (("name", "linhd"))
    map += (("age", 22))
    for ((k, v) <- map) {
      println(k, v)
    }
  }
}
