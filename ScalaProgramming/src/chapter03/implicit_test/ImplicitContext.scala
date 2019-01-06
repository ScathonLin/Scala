package chapter03.implicit_test

/**
  * Created by linhd on 2018/3/8.
  */
object ImplicitContext {

  implicit object OrderingGirl extends Ordering[Girl] {
    override def compare(x: Girl, y: Girl): Int = if (x.faceValue > y.faceValue) 1 else -1
  }

}

class Girl(var name: String, var faceValue: Double) {
  override def toString: String = s"name:$name,faceValue:$faceValue"
}

class MissRight[T: Ordering](val f: T, val s: T) {
  def choose()(implicit ord: Ordering[T]) = if (ord.gt(f, s)) f else s
}

object MissRight {
  def main(args: Array[String]): Unit = {
    import ImplicitContext.OrderingGirl
    val g1 = new Girl("linhd", 90)
    val g2 = new Girl("linxw", 98)
    val mr = new MissRight(g1, g2)
    val result = mr.choose()
    println(result)
  }
}