package com.scala.generic

/**
  * Created by linhd on 2017/12/26.
  */
object TestBoy {
   def main(args: Array[String]) {
      val b1 = new Boy("linhd", 66)
      val b2 = new Boy("shenxiaohan", 100)
      val b3 = new Boy("unknow", 88)
      val arr = Array[Boy](b1, b2, b3)
      //    按照java的思想实现的比较
      arr.sortBy[Boy](x => x).reverse.foreach(boy => println(boy.name))


   }

}
