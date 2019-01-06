package com.scala.conversion

/**
  * Created by linhd on 2017/12/7.
  */
object ImplicitValue {
  def main(args: Array[String]) {
    import Context._
    sayHello()
    sayHello()("love")
  }

<<<<<<< HEAD
  //在Context中找到String类型，然后把linhd赋值给name，而不是shenxiaohan，另外变量名称不一定是name，可以使其他变量名称
  //  scala是通过变量类型来判断的，不是通过名称来判断
=======
  //在Context中找到String类型，然后把linhd赋值给name，而不是shenxiaohan
>>>>>>> ..
  def sayHello()(implicit name: String = "shenxiaohan"): Unit = {
    println(s"hello,$name")
  }
}

//所有的隐式值和隐式方法必须放到object中去
object Context {
  implicit val name: String = "linhd"
<<<<<<< HEAD
}
=======
}
>>>>>>> ..
