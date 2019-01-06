package com.scala.learn.trait_part
/**
 * 特质的学习：第一部分
 * scala与Java一样不支持多继承机制，scala提供的是特质不是接口
 * 特质同时可以拥有抽象方法和具体方法，而类可以实现多个特质
 */
object TraitPart {
  def main(args: Array[String]): Unit = {
    func03()
    //    func02()
  }

  /**
   * 叠加在一起的特质
   */
  def func03() {
    trait Logged {
      /*
       * 这个地方如果在方法后面不加上{}那么相当于方法没被定义，那么子类调用super.log()将会出错，
       * 必须在对应的方法上加上abstract override才行，详细看TraitPart02.scala
       */
      def log(msg: String) {}
    }
    trait TimestampLogger extends Logged with InnerLogger {
      override def log(msg: String) {
        //        super.log(new java.util.Date() + " " + msg)
        /*
         * 如果有两个特质或者多个特质，那么可以指定那个特质进行处理，
         * super[TraitName]进行执行数据交给那个特质进行处理
         */
        super[InnerLogger].log(new java.util.Date() + " " + msg)
      }
    }
    trait InnerLogger extends Logged {
      override def log(msg: String) { super.log("hello:" + msg) }
      /*
       * override def log(msg: String) { println("hello:" + msg) }
       * 如果将super.log()直接换成println(),那么调用流程在InnerLogger的log方法就会终止，
       * 不会再调用下一层级ConsoleLogger的log方法，所以要想按照层级一层一层严格调用，一定要使用super.methodName()
       * 进行方法调用，同时使用super[specifyTrait]进行指定由那个特质进行数据处理的时候，该特质一定是当前特质的
       * 直接超类，不能继承层次中的更远的特质或类
       */
    }
    trait ShortLogger extends Logged {
      val maxLength = 15
      override def log(msg: String) {
        super.log(if (msg.length() < maxLength) msg else msg.substring(0, maxLength - 3) + "...")
      }
    }
    //混入两个特质
    trait ConsoleLogger extends Logged {
      override def log(msg: String) { println(msg) }
    }
    class Account {
      var balance: Double = 0.0
    }
    class SavingAccount extends Account with Logged {
      def withdraw(amount: Double) {
        if (amount > balance) {
          log("Insuffcient funds")
        } else {
          balance -= amount
        }
      }
    }
    /**
     * 注意，上述的日志记录器都将修改过的信息传给了super.log()。
     * 对于特质而言，super.log()并不像类那样拥有相同的含义
     * （如果有相同的意义，那么这些特质将毫无意义--他们扩展自Logged，其log方法什么也不做，
     * 因为按照类的使用语法来看，其调用的是super.log()而Logged的log方法啥也没做），
     * 究其本质来看，super.log()方法调用的其实特质层级中的下一个特质（按照with的顺序“逆序”调用）
     * 也就是说，特质的混合顺序对于程序的运行是有影响的，
     */
    /*
     * ShortLogger先被调用，然后TimestampLogger被调用，最后是ConsoleLogger
     * 对于每一层特质，必须使用super.methodName(args)进行调用，才能按照层级一级一级进行
     * 数据传递和处理，否则数据传递将会被中断
     */
    val acct1 = new SavingAccount with ConsoleLogger with TimestampLogger with ShortLogger
    acct1.balance_=(2000)
    acct1.withdraw(1000)
    println("===========")
    acct1.withdraw(1000)
    println("===========")
    acct1.withdraw(1000)
    println("****验证特质混入顺序对程序运行结果的影响*******")
    /*
     * TimestampLogger先被调用，然后ShortLogger被调用，最后是ConsoleLogger
     */
    val acct2 = new SavingAccount with ConsoleLogger with ShortLogger with TimestampLogger
    acct2.balance_=(2000)
    acct2.withdraw(1000)
    println("===========")
    acct2.withdraw(1000)
    println("===========")
    acct2.withdraw(1000)
  }

  /**
   * 混入特质以及在构造的时候混入特质
   */
  def func02() {
    trait ConsoleLogger {
      def log(msg: String) { println(msg) }
    }
    trait FileLogger {

    }
    class Account {
      var id: Int = 0
      var name: String = "default"
      var balance: Double = 0.0

      def this(id: Int, name: String) {
        this() //不可少这一句
        this.id = id
        this.name = name
      }
      def this(id: Int, name: String, balance: Double) {
        this(id, name)
        this.balance = balance
      }
      def deposit(amount: Double) {

      }
      override def toString(): String = {
        return "Account:[id:" + id + ",name:" + name + ",balance:" + balance + "]"
      }
    }
    /*
		 * 这里的SavingAccount从ConsoleLogger特质中获得了一个具体的log实现，
		 * 如果是Java中接口无法实现的
		 */
    class SavingAccount(id: Int, name: String, balance: Double) extends Account(id, name, balance) with ConsoleLogger {

      def withdraw(amount: Double) {
        if (amount > balance) {
          log("Insufficient funds")
        } else {
          balance_=(balance - amount)
        }
      }
    }
    val account = new SavingAccount(1, "linhd", 2000.0)
    println(account.toString())
    account.withdraw(1000)
    println(account.toString())
    account.withdraw(1000)
    println(account.toString())
    account.withdraw(1000)
    println(account.toString())

    /**
     * 在构造的时候混入新的特质
     */
    new SavingAccount(2, "linxw", 6000.0) with FileLogger
  }

  /*
	 * 不带具体实现的特质
	 */
  def func01() {
    /*
		 * 当做接口使用的特质
		 */
    trait Logger {
      def log(msg: String) //这是个抽象方法
    }
    /*
		 *  方法不需要声明为abstract，未实现的方法默认就是抽象的
		 *
		 *  如果实现多个特质，语法格式如下：
		 *  class ConsoleLogger extends Logger with Cloneable with Serializable
		 *  所有的Java接口都可以作为scala特质进行使用
		 */
    class ConsoleLogger extends Logger { //用extends不是Java中的implements
      def log(msg: String) { println(msg) } //不用写override关键字
    }
  }
}
