import plugins._
import Actions._


  object lab5 {
  def main(args: Array[String]): Unit = {
    println("\n qUnit 'tests':")
    val testString = "Ala  maa Kota"
    val test1 = new Pluginable with Reverting
    val test2 = new Pluginable with LowerCasing
    val test3 = new Pluginable with SingleSpacing
    val test4 = new Pluginable with NoSpacing
    val test5 = new Pluginable with DuplicateRemoval
    val test6 = new Pluginable with Rotating
    val test7 = new Pluginable with Doubling
    val test8 = new Pluginable with Shortening
    println(test1.plugin(testString))
    println(test2.plugin(testString))
    println(test3.plugin(testString))
    println(test4.plugin(testString))
    println(test5.plugin(testString))
    println(test6.plugin(testString))
    println(test7.plugin(testString))
    println(test8.plugin(testString))

    println("\nActions 'unit tests':")
    val test9 = actionA
    val test10 = actionB
    val test11 = actionC
    val test12 = actionD
    val test13 = actionE
    val test14 = actionF
    val test15 = actionG
    println(test9.plugin(testString))
    println(test10.plugin(testString))
    println(test11.plugin(testString))
    println(test12.plugin(testString))
    println(test13.plugin(testString))
    println(test14.plugin(testString))
    println(test15.plugin(testString))


  }


}
