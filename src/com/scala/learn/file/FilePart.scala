package com.scala.learn.file
/**
 * scala文件操作
 */
import scala.io.Source
import java.io.File
import java.io.FileInputStream
import java.io.PrintWriter
import sys.process._

object FilePart {
	def main(args: Array[String]): Unit = {
		visitAllFileInDirectory(new File("D:\\Coding\\Project\\scalaide\\Scala\\src"))
		//		visitDirectory()
		//		writeToTxtFile()
		//		readFromBinaryFile()
		//		readFromURLAndOtherWay()
		//		readFromConsole()
		//		readWordUnitAndNumber()
		//		readWord()
		//		readLine()
	}

	/**
	 * 得到目录下面的所有文件
	 * 递归实现
	 */
	def visitAllFileInDirectory(dir: File) {
		val children = dir.listFiles()
		for (child <- children) {
			if (child.isDirectory()) {
				visitAllFileInDirectory(child)
			} else {
				println(child.getAbsolutePath)
			}
		}
	}

	/**
	 * 获得子目录，不包括非目录文件
	 */
	def visitDirectory() {
		val dir = "D:\\Coding\\Project\\scalaide\\Scala\\src"
		val file = new File(dir);
		val children = file.listFiles().filter(_.isDirectory())
		println(children)
		for (item <- children) println(item.getName, item.getAbsolutePath)
	}

	/**
	 * 写入文本文件
	 */
	def writeToTxtFile() {
		val out = new PrintWriter("test.txt")
		for (i <- Range(1, 10)) out.println(i)

		out.printf("%6d %10.2f", 1000.asInstanceOf[AnyRef], 12.345.asInstanceOf[AnyRef])
		out.close()
	}

	/**
	 * 读取二进制文件，使用的其实是Java类库
	 */
	def readFromBinaryFile() {
		val file = new File("number.txt")
		val in = new FileInputStream(file)
		val bytes = new Array[Byte](file.length().toInt)
		val readLen = in.read(bytes)
		println(readLen)
		//将byte数组转化成字符串
		println(new String(bytes))
		in.close()
	}

	/**
	 * 从URL或者其他源进行读取
	 */
	def readFromURLAndOtherWay() {
		val source1 = Source.fromURL("http://localhost/", "UTF-8")
		val source2 = Source.fromString("Hello,World!")
		val source3 = Source.stdin
		for (line <- source1.mkString.split("\n")) println(line)
		println("====================")
		for (line <- source2) println(line)
		println("====================")
		for (line <- source3) println(line)
	}

	/**
	 * 从控制台读取内容
	 */
	def readFromConsole() {
		val num = readInt()
		println(num)
		val str = readLine()
		println(str)
		val dbl = readDouble()
		println(dbl)
		val flt = readFloat()
		println(flt)
	}

	/**
	 * 读取词法以及单元
	 */
	def readWordUnitAndNumber() {
		val source = Source.fromFile("number.txt", "UTF-8")
		//对字符串进行分割
		val tokens = source.mkString.split(",")
		val numbers = for (w <- tokens) yield w.toDouble
		for (number <- numbers) println(number)
		println("====================")
		val mapNumbers = tokens.map(_.toDouble)
		for (number <- mapNumbers) println(number)
		/*
		 * 打印结果
		 * 12.0
		 * 34.0
		 * 56.0
		 * ====================
		 * 12.0
		 * 34.0
		 * 56.0
		 */
	}

	/**
	 * 读取字符
	 */
	def readWord() {
		val source = Source.fromFile("words.txt", "UTF-8")
		val iter = source.buffered
		val stringBuilder = new StringBuilder()
		while (iter.hasNext) {
			//过滤掉是“l”的字符
			if (!(iter.head == 'l'))
				stringBuilder.append(iter.next())
			else
				iter.next()
		}
		println(stringBuilder.toString())
		/*
		 * 文件内容：
		 * lintl zhangmeili linhuadong linxw dings
		 * 打印结果：
		 * int zhangmeii inhuadong inxw dings 过滤掉了l
		 */
	}

	/**
	 * 读取行
	 */
	def readLine() {
		val source = Source.fromFile("words.txt", "UTF-8")
		val lineIterator = source.getLines()
		println(lineIterator) //non-empty iterator,是一个迭代器
		for (line <- lineIterator) println(line)
		/*
		 * 这个地方如果直接使用上面的source，那么将不会读取出任何内容，
		 * 因为文件流文件流在原来的source中已经达到了文件尾部，所以要重置
		 * 文件流，才能重新读取文件，需要调用source.reset()拿到了一个新的流，
		 * 然后进行操作
		 */
		var bufferSource = source.reset()
		val lines = bufferSource.getLines().toArray
		for (line <- lines) println(line)

		bufferSource = bufferSource.reset()
		val bufLines = bufferSource.getLines().toBuffer
		for (line <- bufLines) println(line)
	}

}