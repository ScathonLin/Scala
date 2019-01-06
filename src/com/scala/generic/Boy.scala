package com.scala.generic

/**
  * Created by linhd on 2017/12/26.
  */
class Boy(val name: String, var faceValue: Int) extends Comparable[Boy] {
  override def compareTo(o: Boy): Int = faceValue - o.faceValue
}
