package chapter03

/**
  * Created by linhd on 2018/3/7.
  */
object OptionDemo {
  def main(args: Array[String]): Unit = {
    val map = Map("name" -> "linhd", "age" -> 22)
    val v = map.get("age") match {
      case Some(i) => i
      case None => 0
    }
    println(v)
    val v1 = map.getOrElse("addr", "shandong")
    println(v1)
  }
}