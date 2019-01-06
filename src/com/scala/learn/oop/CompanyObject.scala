package com.scala.learn.oop

/**
 * 伴生对象,
 * 在Java中，通常会用到既有实例方法又有静态方法的类，在scala中可以通过类和与类同名的“伴生”对象达到同种目的
 */
object CompanyObject {
  private var lastNumber = 0
  private def newUniqueNumber() = {
    lastNumber += 1
    lastNumber
  }
  def main(args: Array[String]): Unit = {
    var o1 = new CompanyObject()
    o1.deposit(100)
    println(o1.id)
    println(o1.current)
    var o2 = new CompanyObject()
    o2.deposit(200)
    println(o2.id)
    println(o2.current)
    var o3 = new CompanyObject()
    o3.deposit(300)
    println(o3.id)
    println(o3.current)
    println("======apply & unapply===========")
    func01()
  }

  /**
   * unapply函数的学习
   */
  def func01() {
    class Money(var value: Double, var country: String) {}
    object Money {
      def apply(value: Double, country: String): Money = {
        new Money(value, country)
      }
      def unapply(money: Money): Option[(Double, String)] = {
        if (money == null) {
          None
        } else {
          Some(money.value, money.country)
        }

      }
    }
    val money = Money(100.0, "RMB")
    money match {
      case Money(num, country) => println(num, country)
      case _ => println("NOT RMB")
    }
  }

}
class CompanyObject {
  //利用对象实现单例对象，实现了账户之间不会重复
  val id = CompanyObject.newUniqueNumber()
  private var balance = 0.0
  def deposit(amount: Double) {
    balance += amount
  }
  def current = balance
}