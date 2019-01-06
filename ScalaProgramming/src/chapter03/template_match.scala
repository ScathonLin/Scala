package chapter03

/**
  * Created by linhd on 2018/1/28.
  */
object template_match {
  def main(args: Array[String]) {
    test08()
    //    test07()
    //    test06()
    //    test05()
    //    test04()
    //    test03(100)
    //    test02()
    //    test01()
  }

  def test08(): Unit = {
    for (i <- Seq(1, 2, 3, 4)) {
      i match {
        case _ if i % 2 == 0 => println(s"even:$i")
        case _ => println(s"odd:$i")
      }
    }
  }

  def test07(): Unit = {
    val langs = Seq(("Scala", "Martin", "Odersky"),
      ("Clojure", "Rich", "Hickey"),
      ("Lisp", "John", "McCarthy"))
    for (tuple <- langs) {
      tuple match {
        case ("Scala", _, _) => println("Found Scala")
        case (lang, first, last) => println(s"lang:$lang,first:$first,last:$last")
        case _ => println("not found")
      }
    }
  }

  def test06(): Unit = {
    val s2 = ("one", 1) +: (("two", 2) +: (("three", 3) +: Nil))
    val map = Map(s2: _*)
    println(map)
  }

  def test05(): Unit = {
    val nonEmptySeq = Seq(1, 2, 3, 4, 5)
    val emptySeq = Seq.empty[Int]
    val nonEmptyList = List(1, 2, 3, 4, 5)
    val emptyList = Nil
    val nonEmptyVector = Vector(1, 2, 3, 4, 5)
    val emptyVector = Vector.empty[Int]
    val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val emptyMap = Map.empty[String, Int]
    Seq(nonEmptySeq, emptySeq, nonEmptyList, emptyList, nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq).foreach(item => println(seqToString(item)))
  }

  def seqToString[T](seq: Seq[T]): String = seq match {
    case head +: tail => s"($head+:" + seqToString(tail) + ")"
    case Nil => "Nil"
  }

  def test04(): Unit = {
    for {
      x <- Seq(1, 2, 2.7, "one", "two", "four")
    } {
      val str = x match {
        case _: Int | _: Double => "a number:" + x
        case "one" => "string one"
        case _: String => "other string:" + x
        case _ => "unexcepted value:" + x
      }
      println(str)
    }
  }

  def test03(y: Int): Unit = {
    for {
      x <- Seq(99, 100, 101)
    } {
      val str = x match {
        case `y` => s"found y:$x"
        case i: Int => "int:" + i
      }
      println(str)
    }
  }

  def test02(): Unit = {
    for {
      x <- Seq(1, 2, 2.7, "one", "two", "four")
    } {
      val tr = x match {
        case 1 => "int 1"
        case i: Int => "other int:" + i
        case d: Double => "a double:" + x
        case "one" => "string one"
        case s: String => "other string:" + s
        case unexcepted => "unexcepted value:" + unexcepted
      }
      println(tr)
    }
  }

  def test01(): Unit = {
    val bools = Seq(true, false, "linhd")
    for (bool <- bools) {
      bool match {
        case true => println("Got heads")
        case false => println("Got tails")
        case _ => println("unknow value type")
      }
    }
  }
}
