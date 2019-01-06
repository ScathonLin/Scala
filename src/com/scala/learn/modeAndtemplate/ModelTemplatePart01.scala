//package com.scala.learn.modeAndtemplate
//
//import scala.math.BigInt
//
///**
//  * 模式匹配和样例类的学习部分01
//  */
//object ModelTemplatePart01 {
//    def main(args: Array[String]): Unit = {
//        func17()
//        //    func16()
//        //    func15()
//        //    func12()
//        //    func11()
//        //    func10()
//        //    func09()
//        //    func08()
//        //    func07()
//        //    func06()
//        //    func05()
//        //    func03()
//        //    func02('6')
//        //    func02('+')
//        //    func02('*')
//        //    func01()
//    }
//
//    /**
//      * 14.17、偏函数
//      * 被包在花括号内的一组case语句是一个偏函数---一个并非对所有输入值都有定义的函数，
//      * 它是PartialFunction[A,B]类的一个实例（A是参数类型，B是返回类型）
//      * 该类有两个方法，apply方法从匹配到的模式计算函数值，而isDefinedAt方法在输入至少匹配其中一个模式时返回true
//      */
//    def func17() {
//        //必须包在花括号中
//        {
//            val f: PartialFunction[Char, Int] = {
//                case '+' => 1;
//                case '-' => -1
//            }
//            println(f('-')) //-1
//            println(f.isDefinedAt('0')) //false
//            //      println(f('0')) //MatchError
//        }
//        println("-3+4".collect { case '+' => 1; case '-' => -1 }) //Vector (-1, 1)
//    }
//
//    /**
//      * 14.16、Option类型
//      * 标准类库中的Option类型用样例类来表示那种可能存在也可能不存在的值，样例子类Some包装了某个值，
//      * 例如，Some("Fred")。而样例对象None表示没有值，这比使用空字符串的意图更加清晰，比使用null表示缺省值更加安全。
//      * Map类的get方法，返回一个Option，如果给定的键没有对应的值，那么返回时None
//      * 如果有值，将该值包装在Some中返回。
//      */
//    def func16() {
//        val scores = Map("lintl" -> 100, "zhangmeili" -> 99)
//        scores.get("linhd") match {
//            case Some(score) => println(score)
//            case _ =>
//                println("No score")
//
//        }
//        println(scores.get("linhd")) //None
//        println(scores.getOrElse("linhd", 0)) //0
//        println(scores.get("lintl")) //Some(100)
//        println(scores.get("lintl").get) //100
//        //可以使用for循环推导式忽略None
//        for (score <- scores.get("linhd")) println(score)
//        scores.get("zhangmeili").foreach(println _)
//    }
//
//    /**
//      * 14.15、模拟枚举
//      */
//    def func15() {
//        sealed abstract class TrafficLightColor
//        case object Red extends TrafficLightColor
//        case object Yellow extends TrafficLightColor
//        case object Green extends TrafficLightColor
//        val colorArr = Array(Red, Yellow, Green)
//        val color = colorArr(scala.util.Random.nextInt(3))
//        color match {
//            case Red => println("stop")
//            case Yellow => println("hurry up")
//            case Green => println("go")
//            case _ => println("unknow color")
//        }
//    }
//
//    /**
//      * 14.14、密封类
//      * 当用样例类做模式匹配的时候，可能想让编译器帮我们确保已经列出了所有可能的选择，
//      * 要达到这个目的，需要将样例类通用的超类声明为sealed
//      */
//    def func14() {
//        sealed abstract class Amount
//        case class Dollar(value: Double) extends Amount
//        case class Currency(value: Double, unit: String) extends Amount
//        /*
//         * 密封类的所有自雷都必须在与该密封类相同的文件中定义，举例来说如果有人想要为欧元添加一个样例类，
//         * 那么必须在Amount被声明的那个文件中完成。
//         * case class Euro(value:Double) extends Amount
//         * 如果某个类是密封的，那么在编译期所有子类都是可知的，
//         * 所以编译器可以检查模式语句的完整性，让所有（同一组）的样例类都扩展某个密封的类或者特质是个好的做法
//         */
//
//    }
//
//    /**
//      * 14.13、
//      * 对于扩展那些其他样例类的样例类而言，toString，equals，hashCode和copy方法不会被生成，
//      * 如果有一个样例类继承自其他样例类，将得到一个编译器警告。Scala未来版本可能会完全禁止这样的继承关系，
//      * 如果需要多层次的继承将样例类的通用行为抽象到样例类外部的话，那么只把继承树的叶子部分做成样例类
//      */
//
//    /**
//      * 14.12、匹配嵌套结构
//      * 样例类常用于嵌套结构
//      */
//    def func12() {
//        abstract class Item
//        case class Article(description: String, price: Double) extends Item
//        case class Bundle(description: String, price: Double, items: Item*) extends Item
//        //不需要使用new创建样例类实例
//        val b1 = Bundle("Father's day special", 20.0)
//        Article("Scala fro the impatient", 39.95)
//        //定义嵌套对象
//        val bundle = Bundle("Another Distillery Sampler", 10, Article("Old Potrero Straight Rye Whisky", 79.95), Article("Junipero Gin", 32.95))
//        bundle match {
//            case Bundle(_, _, Article(desc, _), _*) => println(desc)
//            case _ => println("I don't know")
//        }
//        bundle match {
//            case Bundle(_, _, art@Article(_, _), rest@_*) => println(art, rest)
//            /*
//             * art绑定的是匹配到的第一个Article对象，rest匹配的则是剩余的Item序列
//             * 本例中 _*是必须的，以下模式，case Bundle(_, _, art @ Article(_, _), rest @ _)
//             * 这种情况只适用于一个Article加上不多不少正好一个Item的Bundle，而该变量被绑定到rest变量
//             */
//            case _ => println("I don't know")
//        }
//
//    }
//
//    /**
//      * 14.11 case语句中的中置表示法
//      * 如果unapply方法产出一个对偶，则你可以在case语句中使用中置表示法，尤其是，
//      * 对于有两个参数的样例类，可以使用中置表示法来表示它。
//      */
//    def func11() {
//        /**
//          * unapply函数的学习
//          */
//        class Money(var value: Double, var country: String) {}
//        object Money {
//            def apply(value: Double, country: String): Money = {
//                new Money(value, country)
//            }
//
//            def unapply(money: Money): Option[(Double, String)] = {
//                if (money == null) {
//                    None
//                } else {
//                    Some(money.value, money.country)
//                }
//            }
//        }
//        val money = Money(100.0, "RMB")
//        money match {
//            case Money(num, country) => println(num, country)
//            case _ => println("NOT RMB")
//        }
//        //中置表示法
//        val (num, country) = money match {
//            case num Money country => (num, country)
//            // num Money country == Money(num,country)
//            case _ => println("NOT RMB")
//        }
//        println(num, country)
//    }
//
//    /**
//      * 14.10 copy方法和带名参数
//      */
//    def func10() {
//        case class Dollar(value: Double)
//        case class Currency(value: Double, unit: String)
//        object Dollar {
//            def apply(value: Double): Dollar = {
//                new Dollar(value)
//            }
//        }
//        object Currency {
//            //必须指明返回类型，否则默认是unit类型，那么将没有copy方法
//            def apply(value: Double, unit: String): Currency = {
//                new Currency(value, unit)
//            }
//        }
//        val amt = Currency(100.0, "CHN")
//        /*
//         * 这个方法本身不是很有用，因为毕竟Currency是不可变对象，
//         * 所以完全可以共享这个对象的引用，但是可以用带名参数修改修改某些属性：
//         */
//        val c1 = amt.copy()
//        println(amt == c1) //true
//        val c2 = amt.copy(value = 200.0)
//        println(amt == c2) //false
//        println(c2.value) //200.0
//        val c3 = amt.copy(unit = "ERP")
//        println(amt == c3) //false
//        println(c3.unit) //ERP
//    }
//
//    /**
//      * 14.9、样例类
//      * 样例类是一种特殊的类，它们经过优化被用于模式匹配，
//      * 本例中，有两个扩展自常规（非样例）类的样例类
//      */
//    def func09() {
//        //构造器中的参数都是val，除非它被显式地声明为var（不建议这么做）
//        case class Dollar(value: Double)
//        case class Currency(value: Double, unit: String)
//        //针对单例的样例对象
//        case object Nothing
//
//        val objArr = Array(new Dollar(100.0), new Currency(200.0, "CHN"))
//        val result = objArr(scala.util.Random.nextInt(2)) match {
//            case Dollar(v) => "$" + v
//            case Currency(_, u) => "Oh noes,I got " + u
//            case Nothing => ""
//        }
//        println(result)
//    }
//
//    /**
//      * 14.8for表达式中的模式
//      */
//    def func08() {
//        import scala.collection.JavaConversions.propertiesAsScalaMap
//        //将Java的Properties换转成scala映射---只是为了做出一个有意思的示例
//        for ((k, v) <- System.getProperties)
//            println(k + "->" + v)
//        /*
//         * 将映射中的每一个（键、值）对偶，k被绑定到键，v被绑定到值
//         * 在for循环推导式中，失败的匹配将被安静的忽略，例如，如下循环将打印出所有的值为空白的键，
//         * 跳过所有其他非空白的键,
//         * 也可以使用守卫进行过滤
//         */
//        for ((k, "") <- System.getProperties()) println(k)
//        for ((k, v) <- System.getProperties if v == "") println(k + "->" + v)
//    }
//
//    /**
//      * 14.7、变量声明中的模式
//      */
//    def func07() {
//        val (x, y) = (1, 2)
//        println(x, y)
//        val (q, r) = BigInt(10) /% 3
//        println(q, r) //(3,1),求商以及余数的对偶
//        println((q, r)._1)
//        println((q, r)._2)
//
//        val Array(first, second, _*) = Array(0, 1, 2, 3, 4)
//        println(first, second)
//
//    }
//
//    /**
//      * 14.6、提取器
//      */
//    def func06() {
//        val pattern = "([0-9]+) ([a-z]+)".r
//        var result = "99 bottles" match {
//            case pattern(num, item) => (num, item)
//        }
//        println(result)
//        val rs = pattern.unapplySeq("99 bottles")
//        println(rs)
//    }
//
//    /**
//      * 14.5、匹配数组，列表和元组
//      */
//    def func05() {
//        var arr = Array(1, 2)
//        var result = arr match {
//            case Array(0) => "0" //以0元素开始的数组
//            case Array(x, y) => x + " " + y //含有任意两个元素的数组
//            case Array(0, _*) => "0..." //以0元素开始，任意多个元素的数组
//            case _ => "something else"
//        }
//        println(result)
//        var lst = List(1, 2)
//        result = lst match {
//            case 0 :: Nil => "0" //以0元素开始的列表
//            case x :: y :: Nil => x + " " + y //含有任意两个元素的列表
//            case 0 :: tail => "0..." //以0元素开始，任意多个元素的列表
//            case _ => "something else"
//        }
//        println(result)
//
//        var tpl = Tuple2(0, 1)
//        result = tpl match {
//            case (0, _) => "0..."
//            case (y, 0) => y + " 0"
//            case _ => "neither is 0"
//        }
//        println(result)
//    }
//
//    /**
//      * 14.4、类型模式
//      */
//    def func04() {
//        var obj = 1
//        var sign = 0
//        //编译通不过这里，不知为什么
//        //    sign = obj match {
//        //      case x: Int => x
//        //      case s: String => Integer.parseInt(s)
//        //    }
//    }
//
//    /**
//      * 14.3、模式中的变量
//      * 如果case关键字后面跟着一个变量名，那么匹配的表达式会被赋值给那个变量，
//      * 变量模式可能会与常量表达式想冲突，
//      * 例如：
//      * x match {
//      * case Pi=>...
//      * ...
//      * }
//      * Scala是如何知道Pi是常量，而不是变量呢，背后的规则是：变量必须以小写字母开头，
//      * 如果有一个以小写字母开头的常量，需要将它抱在反引号中：
//      * str match{
//      * case `pathSeparator` =>...
//      * }
//      */
//    def func03() {
//        var str = "linhd123+"
//        var sign = 0
//        for (ch <- str) {
//            ch match {
//                case '+' => sign = 1
//                case '-' => sign = -1
//                //守卫可以使任意Boolean条件
//                case _ if Character.isDigit(ch) => sign = Character.digit(ch, 10)
//                case _ => sign = 0
//            }
//            println(sign)
//        }
//    }
//
//    /**
//      * 14.2、守卫
//      * 假定我们想要扩展我们的示例以匹配所有数字，在类c语言中，将会写出很多case，
//      * scala中可以添加守卫，如下：
//      */
//    def func02(ch: Char) {
//        var sign: Int = 0
//        ch match {
//            case '+' => sign = 1
//            case '-' => sign = -1
//            //守卫可以使任意Boolean条件
//            case _ if Character.isDigit(ch) => sign = Character.digit(ch, 10)
//            case _ => sign = 0
//        }
//        println(sign)
//    }
//
//    /**
//      * 14.1、更好的switch
//      */
//    def func01() {
//        var sign: Int = 0
//        val ch: Char = '+'
//
//        ch match {
//            case '+' => sign = 1
//            case '-' => sign = -1
//            case _ => sign = 0 //相当于default
//        }
//        //另一种写法，match表达式中可以使用任何类型，而不仅仅是数字
//        sign = ch match {
//            case '+' => 1
//            case '-' => -1
//            case _ => 0 //相当于default
//        }
//        /*
//         * 如果没有模式被匹配到，那么将会抛出MatchError错误
//         * scala中模式匹配不会意外进入下一个可以匹配的分之，也就是说不用像c语言后者java那样，
//         * 如果不加break可能会走好几个case
//         */
//        println(sign)
//
//    }
//}