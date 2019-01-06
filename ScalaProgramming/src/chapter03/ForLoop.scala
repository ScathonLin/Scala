package chapter03

/**
  * Created by linhd on 2018/1/28.
  */
object ForLoop {
  def main(args: Array[String]) {
    val people = List("lintl", "zhangml", "linhd", "linxw", "dingsong")
    for {
      person <- people
      if person.startsWith("lin")
      upperNameCase = person.toUpperCase()
    }
    println(upperNameCase)
    val choose = for (person <- people if person.startsWith("lin")) yield person
    choose.foreach(println)
    Option
  }
}
