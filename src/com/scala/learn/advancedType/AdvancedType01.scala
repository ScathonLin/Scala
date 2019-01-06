package com.scala.learn.advancedType

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer
import java.awt.Rectangle
import java.awt.geom.Area
import java.awt.Point
import java.awt.Shape
import scala.io.Source
import javax.imageio.ImageIO
import java.io.File
import java.awt.image.BufferedImage

/**
  * 18、scala高级类型
  */
object AdvancedType01 {
   def main(args: Array[String]): Unit = {
      func09()
      //    func04()
      //    func02()
      //    func01()
   }

   def func09(): Unit = {
      trait X {
         def foo()
      }
      class B {
         self: X =>
      }
      class C extends B with X {
         def foo() = println("self type demo")
      }
      new C().foo()
   }


   /**
     * 18.12、抽象类型
     * 类或特质可以定义一个在子类中被具体化的抽象类型（abstract type）
     *
     */
   def func08() {
      trait Reader {
         type Contents

         def read(fileName: String): Contents
      }
      class StringReader extends Reader {
         type Contents = String

         def read(fileName: String) = Source.fromFile(fileName, "UTF-8").mkString
      }
      class ImageReader extends Reader {
         type Contents = BufferedImage

         def read(fileName: String) = ImageIO.read(new File(fileName))
      }
      //也可以通过类型参数实现
      trait ReaderNew[C] {
         def read(fileName: String): C
      }
      class StringReaderNew extends ReaderNew[String] {
         type Contents = String

         def read(fileName: String) = Source.fromFile(fileName, "UTF-8").mkString
      }

      /**
        * <<<<<<< HEAD
        * 哪种方式更好一些
        * 1、如果类型是在类被实例化的时候给出，那么使用类型参数，比如构造HashMap[String,Int]，
        * 那么会想要在这个时候控制使用的类型，
        * 2、如果类型是在子类中给出的，则使用抽象类型，
        *
        */
      //抽象类型也有类型限定
      trait Listener {
         type Event <: java.util.EventObject
      }
      trait ActionListener extends Listener {
         type Event = java.awt.event.ActionEvent //OK 这是一个子类型
      }
   }

   /**
     * 18.8、存在类型
     * 存在类型被加入scala是为了与Java的类型通配符兼容，存在类型的定义方式是在类型表达式之后跟上forSome{...}
     * 花括号中包含了type和val的声明。
     * Array[T] forSome{type T <: JComponent}
     * ==
     * Array[_ <: JComponent]
     * scala类型通配符只不过是存在类型的语法糖，例如
     * Array[_]
     * 等同于
     * Array[T] forSome {type T}
     **/
   def func07() {
   }

   /**
     * 18.7、中置类型
     * 中置类型是一个带有两个类型参数的类型，以“中置”语法表示，类型名称在两个类型参数之间。
     * String Map Int
     * 而不是
     * Map[String,Int]
     */
   def func06() {
      type *[A, B] = (A, B)

   }

   /**
     * 18.6、复合类型
     * 定义如下：
     * T1 with T2 with T3
     * T1 T2 T3是类型
     * 可以使用复合类型操纵那些必须提供多个特质的值。exg
     * val image = new ArrayBuffer[java.awt.Shape with java.io.Serializable]
     * 这样可以进行绘制Image对象并且也可以对对象进行序列化，因为继承了Serializable接口，
     * 所以可以进行序列化操作
     */
   def func05() {
      val image = new ArrayBuffer[java.awt.Shape with java.io.Serializable]
      val rect = new Rectangle(5, 10, 20, 30)
      image += rect //OK Rectangle is instanceof Serializable
      //    image += new Area(rect) //ERROR Area是Shape但不是Serializable

      trait ImageShape extends java.awt.Shape with java.io.Serializable
      /*
       * 这段代码意味着ImageShape扩展自交集类型Shape with Serializable
       * 可以把结构类型的声明添加到简单类型或者复合类型，
       * 例如：
       */
      val image_new = new ArrayBuffer[Shape with Serializable {def contains(p: Point): Boolean}]

      //该类型的实例必须是Shape的子类型，也是Serializable的子类型，并且有一个contains方法。
      def test(target: Shape with Serializable {def contains(p: Point): Boolean}, str: String) {
         //也可以这么玩
      }
   }

   /**
     * 18.5、结构类型
     * 结构类型就是一组关于抽象方法、字段和类型的规格说明，这些抽象方法、字段和类型是满足该规格的类型必须具备的。
     */
   def func04() {
      def appendLines(target: {def append(str: String): Any}, lines: Iterable[String]) {
         for (line <- lines) {
            target.append(line); target.append("\n")
         }
         /*
          * 可以对任何具备append方法的类的实例调用appendLines方法，这比定义一个Appendable的特质更为灵活
          * scala通过反射机制调用目标类的append方法，
          */
      }

      class StringUtil {
         @BeanProperty
         var strBuilder = new StringBuilder()

         def append(str: String): Unit = {
            strBuilder.append(str)
         }
      }
      val strUtil = new StringUtil()
      appendLines(strUtil, Array[String]("linhd", "like", "shenxiaohan"))
      println(strUtil.getStrBuilder().toString())
   }

   /**
     * 18.4、类型别名
     */
   def func03() {
      class Book {

         import scala.collection.mutable._

         type Index = HashMap[String, (Int, Int)]
      }
      val book = new Book()
      //    book.Index
   }

   /**
     * 18.1.2、单例类型
     */
   def func02() {
      object Title
      class Document {
         private var useNextArgAs: Any = null

         def set(obj: Title.type): this.type = {
            useNextArgAs = obj;
            println("set title");
            this
         }

         def to(arg: String) = if (useNextArgAs == Title) useNextArgAs = arg; else println("...")

         //注意def set(obj:Title)..这是错误的，因为Title指的是单例对象，而不是类型
         def getUseNextArgAs(): Any = {
            useNextArgAs
         }
      }
      class Book extends Document {
         def addChapter(chapter: String): this.type = {
            println("set author");
            this
         }

         def addContent(content: String): this.type = {
            println("set content");
            this
         }
      }
      val book = new Book()
      book.set(Title).to("this is title")
      println(book.getUseNextArgAs())
   }

   /**
     * 18.1.1、方法的串接
     */
   def func01() {
      /*
       * 将set方法串起来
       */
      class Document {
         def setTitle(title: String): this.type = {
            println("set title");
            this
         }

         def setAuthor(author: String): this.type = {
            println("set author");
            this
         }
      }
      val doc = new Document()
      doc.setTitle("title").setAuthor("author")
      /*
       * 如果还有子类的话，就有点麻烦了
       */
      class Book extends Document {
         def addChapter(chapter: String): this.type = {
            println("set author");
            this
         }

         def addContent(content: String): this.type = {
            println("set content");
            this
         }
      }
      val book = new Book()
      //    book.setTitle("title").addAdapter();
      /*
       * 报错在addAdapter方法上，由于book.setTitle()返回的是this，所以将会返回类型Document，
       * 但是Document中并没有addChapter方法，所以报错。
       * 解决办法就是：
       * 声明setTitle的返回类型是this.type，这样返回就是book.type那么就能调用
       * addChapter方法了。串接成功
       */
      book.setTitle("title").setAuthor("author").addChapter("chapter").addContent("content")
   }
}