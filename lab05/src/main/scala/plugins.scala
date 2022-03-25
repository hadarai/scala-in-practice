package plugins
abstract class Pluginable {
  def plugin(text:String) : String = text
}


  trait Reverting extends Pluginable {
    abstract override def plugin(text:String):String = text.reverse
  } //reverts text

  trait LowerCasing extends Pluginable  {
    abstract override def plugin(text:String):String = text.toLowerCase()
  }  //converts all chars in text to lowerCase

  trait SingleSpacing  extends Pluginable {
    abstract override def plugin(text:String):String = text.replace("  ", " ")
  } //removes all duplicated spaces in text, example: “ala ma  kota” => “ala ma kota”

  trait NoSpacing  extends Pluginable {
    abstract override def plugin(text:String):String = {
      for (character <- text
           if character != " ")
        yield character
    }
  }  //remove all spaces in text, example:  “ala ma   kota” => “alamakota”

  trait DuplicateRemoval extends Pluginable  {
    abstract override def plugin(text:String):String = text.toSeq.distinct.unwrap
  }
    //removes all chars which occur more than once, example: “alzaa  cda” => “lzcd”
  trait Rotating  extends Pluginable {
      abstract override def plugin(text:String):String = text.charAt(text.length-1) + text.substring(0, text.length-2)
    }
// rotates text once, example: “abc” => “cab”
trait Doubling  extends Pluginable {
  abstract override def plugin(text:String):String = {
      def aux(xList: List[Char], even:Boolean, acc: List[Char]) :List[Char] = {
        xList match {
          case Nil => acc
          case head:: tail if even => aux(tail, !even, acc ::: List(head, head))
          case head :: tail => aux(tail, !even, acc ::: List(head))
        }
      }
      super.plugin(aux(text.toList, even = false, List()).mkString)
    }
  }//duplicates every second char in text, example: “abcd” => “abbcdd”

 trait Shortening  extends Pluginable {
   abstract override def plugin(text:String):String = text.grouped(2).map(_.head).toList.mkString("")
 } //removes every second char in text, example: “ab cd” => “a d”

