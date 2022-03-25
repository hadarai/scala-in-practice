import utilities.Utils._

class negativeRepetition extends Exception

object lab3 {

  def main(args: Array[String]): Unit = {

    def greater(a:Int, b:Int) = (a*2 ==  b)

    println("\nTest isSorted:")
    val testList1 = List(1,2,3,4,5)
    val testList2 = List(2,4,8,16,32)
    val testList3 = List(1,3,7,4,5,64,34,543,54,3)
    val testList4 = List(5,4,3,2,1)
    println(isSorted(testList1, greater))
    println(isSorted(testList2, greater))
    println(isSorted(testList3, greater))
    println(isSorted(testList4, greater))

    println("\nTest isAscSorted:")
    println(isAscSorted(testList1))
    println(isAscSorted(testList2))
    println(isAscSorted(testList3))
    println(isAscSorted(testList4))

    println("\nTest isDescSorted:")
    println(isDescSorted(testList1))
    println(isDescSorted(testList2))
    println(isDescSorted(testList3))
    println(isDescSorted(testList4))

    println("\nTest foldLeft:")
    println(foldLeft(testList1, 0)((a:Int, b:Int) => a+b))
    println(foldLeft(List("ma","rozowego", "kota"), "Ala") ((a:String, b:String) => (a + " " +  b)))

    println("\nTest length:")
    println(length(testList3))
    println(length(testList4))

    println("\nTest repeated:")
    val exception = new negativeRepetition
    unSafe(exception)
    {
      val f = repeated((a: Int) => a + 3, 4)
      println(f(1))
    }
    //my repeated works a slightly diffrent, check implementation

    def add1(a: Int, b: Int) = a + b
    def add2(a: Int)( b: Int) = a + b
    println("\nTest curry:")
    println(curry(add1)(7)(8))
    println("\nTest uncurry:")
    println(uncurry(add2)(7,8))
  }
}
