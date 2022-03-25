import pizzeria._
import orders._

object Main {
  def main(args: Array[String]): Unit = {

    val testPizza1 = Pizza(new margherita, new small, new thin)
    val testPizza2 = Pizza(new pepperoni, new regular, new thick, new salami)
    val testPizza3 = Pizza(new funghi, new large, new thick, new salami, new garlic)
    val testPizza4 = Pizza(new pepperoni, new small, new thick, new noMeat, new ketchup)
    val listOfTestPizzas = List(testPizza1, testPizza2, testPizza3, testPizza4)

    for (test: Pizza <- listOfTestPizzas) {
      println(test + " priced at " + test.price.toString() + "$")
    }

    println("\n\n")
    val testorder1 = new Order("Max",
      "Sloneczna 10 Wroclaw",
      "(+48)123-456-789",
      listOfTestPizzas,
      List(new lemonade, new lemonade),
      Option(new student),
      "Ring doesnt work, please knock!")

    val testorder2 = new Order("Pawel",
      "Garnizonowa 5 Wroclaw",
      "123456789",
      List(testPizza1, testPizza3),
      List(new lemonade, new lemonade, new lemonade, new lemonade, new lemonade),
      Option(new senior))

    val testorder3 = new Order("Pawel",
      "Garnizonowa 5 Wroclaw",
      "+48123456789",
      List(testPizza1, testPizza4),
      List())

    val listOfTestOrders = List(testorder1, testorder2, testorder3)

    for (test: Order <- listOfTestOrders) {
      println(test.toString())
      println("price of extra meat: " + test.extraMeatPrice.getOrElse(0) + "$" )
      println("price of only pizzas: " + test.pizzasPrice.getOrElse(0) + "$")
      println("price of drinks alone: " + test.drinksPrice.getOrElse(0) + "$")
      println("price of only pepperonis: " + test.priceByType(pepperoni()).getOrElse(0) + "$")
      println("Whole order price: " + test.price + "$")
    }
  }
}
