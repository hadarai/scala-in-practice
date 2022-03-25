import MoneyOperations._
import money.Money

//import org.specs2.Specification

object Main {

  def moneyTest() {

    implicit val currencyConverter: CurrencyConverter = CurrencyConverter(Map(
      (EUR, USD) -> (1.07906: BigDecimal),
      (EUR, PLN) -> (4.54282: BigDecimal),
      (USD, EUR) -> (0.926747: BigDecimal),
      (USD, PLN) -> (4.20954: BigDecimal),
      (PLN, EUR) -> (0.220127 : BigDecimal),
      (PLN, USD) -> (0.237556: BigDecimal)
    ))

    val x : Money = 1(USD)
    println(x)

    val sum1: Money = 100.01 (USD) + 200 (EUR)  //result in dollars (most left type)
    val sum2: Money =100.01 (zl) + 200 ($)  //result in złoty (mostleft type)
    val sum3: Money = 5 (zl) + 3 (PLN) + 20.5 (USD)   //result in złoty(most left type)

    val sub: Money= 300.01 (USD) - 200 (EUR) //result in dollars (most left type)

    val mult1: Money= 30 (zl) * 20 //result in złoty
    val mult2: Money= 20 ($) * 11 //result in dollars

    val conv1: Money= 150.01 (USD) as PLN // converts to złoty
    val conv2: Money= 120.01 (USD) as e // converts to euro

    val compare1: Boolean = 300.30(USD) > 200(e)
    val compare2: Boolean = 300.30($) < 200(EUR)

    val listOfTestValues = List(sum1, sum2, sum3, sub, mult1, mult2, conv1, conv2, compare1, compare2)

    for (testValue <- listOfTestValues){
      println(testValue)
    }
  }

  def main(args: Array[String]): Unit = {

    println("Tests:")
    moneyTest()

  }
}