/**
  * Created by linhd on 2017/12/25.
  */
object StringUtilV1 {
  def joiner(strings: String*): String = strings.mkString("-")

  def joiner(strings: List[String]): String = joiner(strings: _*)
}
println(StringUtilV1.joiner(List("Programming", "Scala")))
def makeList(strings: String*) = {
  if (strings.length == 0) {
    //    List(0)
    List.empty[String]
  } else {
    strings.toList
  }
}
val list: List[String] = makeList()
def mul(i: Int) = {
  i * i
}
mul(2)
def hello(name: String) = {
  s"""
     |Welcome!
     |Hello,$name!
     |*(Gratuitous Sta!!)
     |We're gald you're here.
     |Have some extra whitespace.
   """.stripMargin
}
hello("linhd")
def goodbye(name: String) = {
  s"""xxxGoodBye,${name}yyy
      xxxCome again!yyy""".stripPrefix("xxx").stripSuffix("yyy")
}
goodbye("linhd")
val f1: (Int, String) => String = (int, string) => String.valueOf(int) + string
val f2: Function2[Int, String, String] = (int, string) => String.valueOf(int) + string

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund", "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
for {
  breed <- dogBreeds
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)

val dogBreeds2 = List(Some("linhd"), None, Some("linhd123"), None)
for {
  Some(breed) <- dogBreeds2
  upcaseBreed = breed.toUpperCase()
} println(upcaseBreed)