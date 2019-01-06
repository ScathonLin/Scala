package com.scala.learn.trait_part

import java.io.PrintStream
import java.io.PrintWriter
import java.util.Date

/**
 * 特质学习第二部分
 */
object TraitPart02 {
  def main(args: Array[String]): Unit = {
    func06()
    //    func05()
    //    func04()
    //    func02()
    //    func01()
  }

  /**
   * 扩展类的特质（特质继承了某个或者某些类）+自身类型
   */
  def func07() {
    trait Logged {
      def log(msg: String) {}
    }
    trait LoggedException extends Exception with Logged {
      def log() { log(getMessage()) }
    }
    class UnhappyException extends LoggedException {
      override def getMessage() = "arggh"
    }
    /**
     * 继承关系：
     * Exception       LoggedException<<interface>>
     * 		^										^
     * 		|-------------------|
     * 		|
     * UnhappyException
     *
     * 特质的超类自动成为任何混入该特质的类的超类
     * 有一种情况：
     * 如果我们的类已经扩展了另一个类怎么办？没关系，只要已经扩展的那个类是特质的超类的一个子类就行了。
     * 举个例子：
     * class UnhappyException extends IOException with LoggedException
     * 这里的UnhappyException扩展自IOException，而IOException是Exception的子类
     * （或者说IOException扩展自Exception），那么混入特质以后，它的超类已经在那里了，不需要额外添加，见下图
     *
     * Exception       LoggedException<<interface>>
     * 		^												^
     * 		|												|
     * 		|												|
     * IOException								|
     * 		|--超类Exception已经在了---
     * 		|
     * UnhappyException
     *
     *
     * 但是如果扩展一个不相关的类，那么就不能混入这个特质了，例如
     * class UnhappyException extends JFrame with LoggedException
     * 错误！因为无法同时将JFrame以及Exception同时作为超类（JVM单继承机制，这也是为什么要求扩展的类必须是特质扩展类的子类）
     */

    //============自身类型的学习===================
    /*
     * 1、当特质扩展类时，编译器能够确保的一件事是所有混入该特质的类都认这个类作为超类，
     * Scala还有另一套机制可以保证这一点，就是：自身类型；
     * 当特质以如下代码开始定义的时候：
     * this:类型=>
     * 它便只能被混入指定类型的子类
     * 
     * 2、自身类型也能处理结构类型
     * 这种类型只给出类必须拥有的方法，而不是类的名称，看示例代码
     */
    trait LoggedException_new extends Logged {
      this: Exception =>
      def log() { log(getMessage()) }
    }
    /*
     * 注意！
     * 上面这个类LoggedException_new并不是扩展自Exception类，
     * 而是有一个自身类型的任何方法，举例来说，log方法中的getMessage()调用就是合法的，
     * 因此我们知道this必定是一个Exception，如果你想把这个特质混入一个不符合自身类型要求的类，
     * 就会报错：
     * val f=new JFrame with LoggedException
     * ERROR!因为Exception是LoggedException的自身类型，而JFrame并不是Exception的子类型
     */
    
    //自身类型处理结构类型
    
    trait LoggedException_new2 extends Logged{
      this:{def getMessage():String}=>
        def log(){
          log(getMessage())
        }
    }
  }

  /**
   * 初始化特质中的字段
   * 特质构造器不能有构造器参数，每个特质都有一个无参数构造器。
   * 缺少构造器参数是特质与类之间唯一的技术差别，除此之外，特质可以具备类的所有特性，
   * 比如，具体的和抽象的字段，以及超类
   * val acc=new SavingAccount with FileLogger("app.log")
   * 这是错误的语句
   */
  def func06() {
    trait Logger {
      def log(msg: String)
    }
    trait FileLogger extends Logger {
      /*
       * 为了解决无法传参的问题，可以使用一个抽象字段，由子类进行赋值，
       * 但是这样有一个陷阱，如下：
       */
      val fileName: String
      val out = new PrintStream(fileName)
      //lazy val out = new PrintStream(fileName) //一种解决办法，懒值加载
      def log(msg: String) { out.println(msg); out.flush() }
    }
    class Account {
      var balance = 2000.0
    }

    abstract class SavingAccount extends Account with FileLogger {
      val maxLength: Int = 10
      def withdraw(amount: Double) {
        if (amount > balance) log("Insufficient funds")
        else balance_=(balance - amount)
      }
    }

    //错误的处理方式，抛出空指针异常
    val error = new SavingAccount with FileLogger {
      val fileName = "myapp.log" //由子类定义文件名称
      /*
    	 * 问题出在了构造顺序上面，
    	 * 因为由构造特质的顺序知道，此例中，构造顺序依次是：
    	 * Account->Logger->FileLogger->SavingAccount
    	 * 从中可以看出，FileLogger构造早于SavingAccount，那么FileLogger在执行
    	 * val out=new PrintStream(fileName)的时候，由于fileName压根就没有初始化，
    	 * 所以会报出空指针异常，一种解决办法就是采用  “提前定义”，下面是正确的方式 
    	 */
    }
    //解决办法之一：，提前定义的方法，代码不漂亮但是解决了问题
    val right = new {
      val fileName = "myapp.log"
    } with SavingAccount with FileLogger
    right.withdraw(1000)
    right.withdraw(1000)
    right.withdraw(1000)

    /*
     * 解决办法之二：在FileLogger构造器中使用懒值（lazy load）
     * 如此一来，out字段只有在使用的时候才会被初始化，在那个时候，fileName已经被设置好值了，
     * 所以不会抛出空指针异常，但是由于懒值在每次使用前都会检查是否已经初始化，所以使用起来并不是那么高效
     * trait FileLogger extends Logger {
     * 	val fileName: String
     * 	lazy val out = new PrintStream(fileName)
     * 	def log(msg: String) { out.println(msg); out.flush() }
     * }
     */
  }

  /**
   * 特质构造顺序
   */
  def func05() {
    trait Logger {
      def log(msg: String) {}
    }
    trait FileLogger extends Logger {
      val out = new PrintWriter("app.log")
      out.println("# " + new Date().toString()) //同样是特质构造器的一部分
      override def log(msg: String) { out.println(msg); out.flush() }
    }
    trait ShortLogger extends Logger {
      val maxLength: Int //这是个抽象字段，只是声明，并没有进行初始化
      abstract override def log(msg: String) {
        super.log(if (msg.length() <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
      }
    }

    /*
     * 这些语句在任何混入该特质的对象构造的时候都会被执行，
     * 构造器以如下的顺序执行：
     * 1、首先调用超类的构造器
     * 2、特质构造器在超类构造器之后、类构造器之前执行
     * 3、特质由左向右依次被构造
     * 4、每个特质当中，父特质先被构造
     * 5、如果多个特质共有一个父特质，而那个父特质已经被构造，则不会被再次构造。
     * 6、所有特质构造完毕，子类被构造
     * example：
     * class SavingAccount extends Account with FileLogger with ShortLogger
     * 构造顺序：
     * Account(超类)->
     * Logger（第一个特质的父特质）->
     * FileLogger（第一个特质）->
     * ShortLogger（第二个特质的父特质Logger已经被构造，所以不用重新构造）->
     * SavingAccount（本类）
     * 在ShortLogger中调用super.log()会调用FileLogger的方法，
     * FileLogger中调用super.log()方法会执行Logger的方法，所以调用顺序是线性反向执行
     */
    class Account {
      var balance = 0.0
    }

    abstract class SavingAccount extends Account with FileLogger with ShortLogger {
      val maxLength: Int = 10
      def withdraw(amount: Double) {
        if (amount > balance) log("Insufficient funds")
        else balance_=(balance - amount)
      }
    }
    val acc1 = new SavingAccount with FileLogger with ShortLogger
    acc1.balance_=(2000)
    acc1.withdraw(1000)
    acc1.withdraw(1000)
    acc1.withdraw(1000)
  }

  /**
   * 特质中的抽象字段
   * 特质中未被初始化的字段在具体的子类中必须被重写
   * 未被初始化的方法在子类中如果被调用那么该方法就是抽象的，必须加上abstract关键字
   */
  def func04() {
    trait Logged {
      def log(msg: String)
    }
    trait ConsoleLogger extends Logged {
      override def log(msg: String) {
        println(msg)
      }
    }
    trait ShortLogger extends Logged {
      val maxLength: Int //这是个抽象字段，只是声明，并没有进行初始化
      abstract override def log(msg: String) {
        super.log(if (msg.length() <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
      }
    }

    class Account {
      var balance = 0.0
    }

    abstract class SavingAccount extends Account with ConsoleLogger with ShortLogger {
      /*
       * 不加这一句会报错，报错如下：
       * class SavingAccount needs to be abstract, 
       * since value maxLength in trait ShortLogger of type Int is not defined
       * 原因就是ShortLogger中的maxLength并没有进行初始化，是抽象字段，所以子类要进行重写
       * 但是不用加override关键字
       */
      val maxLength: Int = 10 //不用加override关键字
      def withdraw(amount: Double) {
        if (amount > balance) log("Insufficient funds")
        else balance_=(balance - amount)
      }
    }
    val acc1 = new SavingAccount with ConsoleLogger with ShortLogger
    println(acc1.maxLength)

  }

  /**
   * 特质中的具体字段
   */
  def func03() {
    trait Logged {
      def log(msg: String)
    }
    trait ConsoleLogger extends Logged {
      override def log(msg: String) {
        println(msg)
      }
    }
    trait ShortLogger extends Logged {
      val maxLength = 15
      /*
       * 如果在方法体中调用super.log()那么会报错，因为super.log()压根就没定义，只是声明了一下，并没有方法体，
       * 所以必须在该方法加上abstract关键词进行抽象标明
       */
      abstract override def log(msg: String) {
        super.log(if (msg.length() <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
      }
    }
    class Account {
      var balance = 0.0
    }
    class SavingAccount extends Account with ConsoleLogger with ShortLogger {
      /*
       * SavingAccount类按正常方式继承了这个balance字段，SavingAccount对象由所有超类的字段，
       * 以及任何子类中定义的字段构成，在JVM中，一个类只能扩展一个超类，因此来自“特质”的字段不能
       * 以相同的方式继承，由于这个限制，maxLength被直接加到了SavingAccount类中，
       * 与interest字段排在了一起，也就是当前的对象含有两个字段，分别是自身的interest，
       * 以及来自特质中的maxLength（非正常继承，因为JVM单继承机制）
       */
      val interest = 0.0
      def withdraw(amount: Double) {
        if (amount > balance) log("Insufficient funds")
        else balance_=(balance - amount)
      }
    }

  }

  /**
   * 当做富接口使用的特质
   * 特质可以包含大量的工具方法，这些工具方法可以以来一些抽象方法来实现
   */
  def func02() {
    trait Logger {
      def log(msg: String)
      def info(msg: String) { log("INFO:" + msg) }
      def warn(msg: String) { log("WARN:" + msg) }
      def severe(msg: String) { log("SEVERE:" + msg) }
    }
    class Account {
      var balance: Double = 0.0
    }
    class SavingAccount extends Account with Logger {
      def withdraw(amount: Double) {
        if (amount > balance) severe("Insufficient funds")
        else balance_=(balance - amount)
      }
      //如果不重写这个log方法的话，那么就必须声明SavingAccount类为abstract
      override def log(msg: String) {
        println(msg)
      }
    }
    val acc1 = new SavingAccount
    acc1.balance_=(2000)
    acc1.withdraw(1000)
    acc1.withdraw(1000)
    acc1.withdraw(1000)

  }

  /**
   * 在特质中重写抽象方法
   */
  def func01() {
    trait Logger {
      def log(msg: String) // this is a abstract method
    }
    trait TimestampLogger extends Logger {
      /*
       * 因为super.log未被定义，TraitPart01中的代码之所以能够跑起来，
       * 是因为super.log()方法体是空的，但是仍然是被定义的方法
       * scala认为TimestampLogger依旧是抽象的--他需要混入一个具体的log方法，
       * 因此必须给方法打上abstract关键字以及override关键字，如下所示：
       */
      abstract override def log(msg: String) { //override method of super trait
        super.log(new java.util.Date() + " " + msg) //super.log haven't been defined,It's a error
      }
    }
  }
}
