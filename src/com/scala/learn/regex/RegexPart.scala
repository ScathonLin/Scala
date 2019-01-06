package com.scala.learn.regex
/**
 * 正则表达式入门
 */
object RegexPart {
	def main(args: Array[String]): Unit = {
		func01()
	}
	def func01() {
		val numPattern = "[0-9]+".r
		val wsnumwsPattern = """\s+[0-9]+\s""".r
		for (matchString <- numPattern.findAllIn("99 bottles,98 bottles")) println(matchString)
		println("===================")
		val matchArr = numPattern.findAllIn("99 bottles,98 bottles").toArray
		for (str <- matchArr) println(str)
		println("===================")
		for (matchString <- numPattern.findFirstIn("99 bottles,98 bottles")) println(matchString)
		println("===================")
		for (matchString <- numPattern.findPrefixOf("99 bottles,98 bottles")) println(matchString)
		println("===================")
		for (matchString <- numPattern.findPrefixOf("99 bottles,98 bottles")) println(matchString)
		println("===================")
		for (matchString <- wsnumwsPattern.findPrefixOf("99 bottles,98 bottles")) println(matchString)

		var result = numPattern.replaceFirstIn("99 bottles,98 bottles", "666")
		println(result)
		result = numPattern.replaceAllIn("99 bottles,98 bottles", "666")
		println(result)

		val numitemPattern = "([0-9]+) ([a-z]+)".r
		val numitemPattern(num, item) = "99 bottles"
		println(num, item)
		println("===================")
		for (numitemPattern(num, item) <- numitemPattern.findAllIn("99 bottles,98 bottles")) println(num, item)
	}
}