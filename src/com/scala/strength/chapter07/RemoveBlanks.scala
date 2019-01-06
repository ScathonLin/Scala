package com.scala.strength.chapter07

import org.junit.{Before, Test}

@Test
class RemoveBlanks {

   val fileRootPath = this.getClass.getResource("/")

   /**
     * 从指定文件中移除空行
     */
   def apply(path: String, compressWhiteSpace: Boolean = false): Seq[String] = {
      for {
         line <- scala.io.Source.fromFile(fileRootPath + "/data/scala_lang_design_tst_data/chapter-07/input-1.txt").getLines().toSeq
         if !line.matches("""^\s*$""")
         line2 = if (compressWhiteSpace) line replaceAll("\\s+", " ")
         else line
      } yield line2
   }

   /**
     * 从指定的输入文件移除空行，并将其他内容一次发送给标准输出
     */
   @Test
   def execRemove() = {

   }

}
