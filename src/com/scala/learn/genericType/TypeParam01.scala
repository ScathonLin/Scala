package com.scala.learn.genericType

/**
 * 17、类型参数的学习（泛型）
 */
object TypeParam01 {
  def main(args: Array[String]): Unit = {
    func06()
    //    func04()
    //    func03()
    //    func02()
  }

  /**
   * 17.9、型变
   * Student是Person的子类，但尽管如此，Pair[Student]和Pair[Person]
   * 之间没有任何关系，这和Java中的泛型是一致的
   * 如果想让二者有继承关系，必须在Pair类表明这一点。
   */
  def func08() {
    class Pair[+T](val first: T, val second: T)
    /*
     * +号意味着该类型是与T“协变”的--也就是说，它与T按同样的方向型变，由于Student是Person的子类，
     * 那么Pair[Student]也就是Pair[Person]的子类型了。
     */
     
  }

  /**
   * 17.8、类型约束
   * T =:= U   T是否等于U
   * T <:< U   T是否是U的子类型
   * T <%< U	 T能否被视图（隐式）转换为U
   *
   */
  def func07() {
    class Pair[T](val first: T, val second: T) {
      def smaller(implicit ev: T <:< Ordered[T]) =
        if (first < second) first else second

    }
  }

  /**
   * 17.7、多重界定
   * 类型变量同时有上界和下界
   * T>:Lower<:Upper
   * 不能同时有多个上界或多个下界，不过，依然可以要求一个类型实现多个特质
   * T<:Comparable[T with Serializable with Cloneable
   * 可以有多个视图界定
   * T <% Comparable[T <% String
   * 可以有多个上下文界定
   * T:Ordering:Manifest
   */

  /**
   * 17.6、Manifest上下文界定
   * 要实例化一个Array[T]。需要一个Manifest[T]对象
   */
  def func06() {
    def makePair[T: Manifest](args: T*): Array[T] = {
      val r = new Array[T](args.length);
      var i = 0
      for (arg <- args) {
        r(i) = arg
        i += 1
      }
      r
    }

    println(makePair(1, 2).mkString(","))
    println(makePair(1, 2, 3).mkString(","))
  }

  /**
   * 17.5、上下文界定
   * 视图界定T<%V要求必须存在一个从T到V的隐式转换，上下文界定的形式为T:M，其中M是另一个泛型类，
   * 它要求必须存在一个类型为M[T]的“隐式值”。
   */
  def func05() {
    class Pair[T: Ordering]
    /*
     * 上述定义要求必须存在一个类型为Ordering[T]的隐式值，该隐式值可以被用在该类
     * 的方法中，当生声明一个使用隐式值的方法时，需要添加一个隐式参数，
     */
    class PairNew[T: Ordering](val first: T, val second: T) {
      def smaller(implicit ord: Ordering[T]) =
        if (ord.compare(first, second) < 0) first else second
    }
  }

  /**
   * 17.4、视图界定
   */
  def func04() {
    class Pair[T <: Comparable[T]](o1: T, o2: T) {
    }
    //    val pair = new Pair(4,2)
    /*
     * 上面一句代码是错误的，因为在scala中Int是没有实现Comparable接口的，
     * 这一点与Java的Integer不同，不过，RichInt实现了Comparable[Int]。同时有一个
     * Int到RichInt的隐式转换，
     * 解决办法就是使用视图界定：
     * 
     */
    class PairNew[T <% Comparable[T]](val o1: T, val o2: T) {
      //      def smaller = if (o1 < o2) o1 else o2
      /*
       * 这样是错误的，不能直接进行关系操作，要想这样做得实现Ordered[]，
       * 有了视图界定，字符串可以被隐式转换成RichString，而RichString是Ordered[String]的子类型
       * 代码如下：
       */
    }
    //<%关系意味着T可以被隐式转换成Comparable[T]
    val pair = new PairNew(1, 2)

    //使用Ordered视图界定
    class PairNew2[T <% Ordered[T]](val o1: T, val o2: T) {
      def smaller = if (o1 < o2) o1 else o2
    }

    println(new PairNew2("Linhd", "Shenxiaohan").smaller)

  }

  /**
   * 17.3、类型变量的界定
   * 有时候需要对类型变量进行限制，比如将pair类型的两个组件的类型相同
   * class Pair[T](val first:T,val second:T),
   * 现在想添加一个方法，产出比较小的值
   * class Pair[T](val first:T,val second:T){
   * 		def smaller = if(first.compareTo(second)<0) first else second//ERROR
   * }
   * 这是错的，因为first并一定有compareTo方法，所以解决这个问题的办法就是
   * 添加一个上界：T<:Comparable[T]
   */
  def func03() {
    class Pair[T <: Comparable[T]](val first: T, val second: T) {
      def smaller = if (first.compareTo(second) < 0) first else second
    }
    class Pair_New[T](val first: T, val second: T) {
      //替换组件，这种方式不是最好的。
      def replaceFirst(newFirst: T) = new Pair_New[T](newFirst, second)
      //指定一个下界
      def replaceFirstGood[R >: T](newFirst: R) = new Pair_New[R](newFirst, second)
    }

    val compare = new Pair("Linhd", "Shenxiaohan").smaller
    println(compare)

  }

  /**
   * 17.2、泛型函数
   */
  def func02() {
    def getMiddle[T](a: Array[T]) = a(a.length / 2)
    //和泛型一样，需要把类型参数放在方法名之后
    println(getMiddle(Array("Linhuadong", "Like", "Shenxiaohan")))
    val f = getMiddle[String] _ //这是具体的函数，保存到f中
    println(f(Array("Coding", "For", "You")))
  }

  /**
   * 17.1、泛型类
   */
  def func01() {
    //scala推断类型
    val p1 = new Pair(22, "linhd")
    //自己指定类型
    val p2 = new Pair[Any, Any](22, "linhd")
  }
}