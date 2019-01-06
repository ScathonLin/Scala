package learn_second

/**
  * Created by linhd on 2018/4/26.
  */
object Demo01 {
  def main(args: Array[String]): Unit = {
    func01()
  }

  def func01(): Unit = {
    val name = "Linhd"
    val result = name.exists(_.isUpper)
    println(result)
  }
}