import pizzeria._
package orders
{
  class Order(name: String,
              address: String,
              phone:String, //mandatory validated phone-number (hint: regex)
              pizzas:List[Pizza],
              drinks:List[drink],
              discount:Option[discount] = None,
              specialInfo: String = "" //optional text, like: “Ring doesnt work,   please knock”
             ) {
    require(phone.matches("^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*$"), "incorrect phone number format")
    val amountOfLemonade = {
      var am = 0.0
      for (dr <- drinks
           if(dr.isInstanceOf[lemonade])) am += 1
      am
    }
    override def toString() = {
      val pizzasToString = for (pizza <- pizzas) yield {
        pizza.toString()
      }
      ("\nHi " + name +
        "!\nYou have ordered:\n" + pizzasToString.mkString("\n")
        +"\nwith this amount of lemonade: " + amountOfLemonade + "\nOn address: " +
        address +"\nTel: " + phone + "\nwith discount: " +
        discount.toString().replace("())", "").replace("Some(", "") +  "\nand special info: " + specialInfo)
    } //pretty print the order
    def extraMeatPrice: Option[Double] = {
      var sum: Double = 0.0
      for (pizza <- pizzas) {
        sum += pizza.meatPrice
      }
      Option(sum)
    }
    def pizzasPrice: Option[Double] = {
      var price:Double = 0.0
      for (pizza <- pizzas) price += pizza.price
      Option(price)
    }
    def drinksPrice: Option[Double] = Option((amountOfLemonade * 2):Double)
    def priceByType(typ:pizzaType): Option[Double] = {
      var finalPrice :Double = 0.0
      for (pizza <- pizzas
           if (((pizza.pizzaType).toString()) == (typ.toString()))) finalPrice += pizza.price
      Option(finalPrice)
    }//total price of all pizzas by typ (Margarita, Pepperoni & Funghi)

    val price: Double = //0.0
    {
      discount.toString() match {
        case "Some(student())" => (((pizzasPrice.getOrElse(0.0) :Double) * 0.95) + (drinksPrice.getOrElse(0.0) :Double))
        case "Some(senior())" => (pizzasPrice.getOrElse(0.0) + drinksPrice.getOrElse(0.0)) * 0.93
        case _ => pizzasPrice.getOrElse(0.0)  + drinksPrice.getOrElse(0.0)
      }
    }
    //total price of order. When discount=student than price for all pizzas is reduced by -5%&
    // if discount=senior than price for all pizzas & drinks is reduced by -7%
  }

}

