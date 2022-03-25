package pizzeria

abstract class size
case class small() extends size
case class regular() extends size
case class large() extends size

abstract class pizzaType() {}
case class margherita() extends pizzaType
case class pepperoni() extends pizzaType
case class funghi() extends pizzaType

abstract class crust() {}
case class thin() extends crust
case class thick() extends crust

abstract class topping() {}
case class ketchup() extends topping
case class garlic() extends topping
case class noTopping() extends topping

abstract class meat() {}
case class salami() extends meat
case class noMeat() extends meat

abstract class drink() {}
case class lemonade() extends drink

abstract class discount() {}
case class student() extends discount
case class senior() extends discount

case class Pizza(pizzaType: pizzaType,
                 size: size,
                 crust: crust,
                 extraMeat: meat = new noMeat, //optional meat
                 extraTopping: topping = new noTopping) //optional topping
{
  override def toString() :String = {
    val meat :String = extraMeat.toString() match {
      case "salami()" => "with salami"
      case _ => ""
    }
    val topping :String = "and with " + extraTopping.toString() match {
      case "ketchup()" => "ketchup"
      case "garlic()" => "garlic"
      case _ => ""
    }
    "I'm wonderful " + pizzaType.toString().replace("()", "") + ", " + size.toString().replace("()", "") +
      " sized on " + crust.toString().replace("()", "") + " crust " + meat + topping
  }

  val meatPrice :Double = extraMeat.toString() match {
    case "salami()" => 1
    case _ => 0
  }
  val price:Double = {
    val sizeMultiplyer :Double= size.toString() match {
      case "small()" => 0.9
      case "regular()" => 1.0
      case "large()" => 1.5
    }
    val typePrice:Double = pizzaType.toString() match {
      case "margherita()" => 5
      case "pepperoni()" => 6.5
      case "funghi()" => 7
    }
    val toppingPrice :Double= extraTopping.toString() match {
      case "ketchup()" => 0.5
      case "garlic()" => 0.5
      case _ => 0
    }
    sizeMultiplyer * (meatPrice + toppingPrice + typePrice)
  }  //calculated price for pizza. When type=small than price is 90% & if type=large than price is 150%
}
/*
I'm really not satisfied with matching everything by String but straightaway matching didn't work :(
for example this:
val sizeMultiplyer :Double =
  size match {
      case small => 0.9
      case regular => 1.0
      case large => 1.5
    }
I have no idea why.

Plus orignally I wanted everyting to be like this:
 case class size()
object small extends size
object regular extends size
object large extends size
Which looks a bit cleaner, but then match wouldn't work even more. Idk why.

match is pointless in this language if you can't match any of my own types. This really makes me angry.

Plus IntelliJ all the time told ma about case class this: Class 'margherita' must either be declared abstract or implement abstract member 'equals(that: Any): Boolean' in 'scala.Equals'
I have no idea what he means, maybe you can tell me. (I tried File>Invalidate Caches/Restart)
 */
