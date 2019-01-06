package com.scala.generic

/**
  * Created by linhd on 2017/12/26.
  */
object MyPredef {

  //  这种形式是可以的
  implicit object GirlOrdering extends Ordering[Girl] {
    def compare(x: Girl, y: Girl) =
      if (x.faceValue < y.faceValue) -1
      else if (x.faceValue == y.faceValue) 0
      else 1
  }

  //  这种方式也行，用变量表示
  implicit val girlOrdering = new Ordering[Girl] {
    override def compare(x: Girl, y: Girl): Int = {
      return x.faceValue - y.faceValue
    }
  }
}
