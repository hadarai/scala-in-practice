import money.Money

package object MoneyOperations {

  trait Currency

  case object PLN extends Currency
  case object zl extends Currency
  case object EUR extends Currency
  case object e extends Currency
  case object USD extends Currency
  case object $ extends Currency

  def convertCurrencySymbol(currency: Currency): Currency = currency match{
    case USD | `$` => USD
     case PLN | `zl` => PLN
     case EUR | `e` => EUR
    case _ => throw new Exception("Wrong currency symbol")
  }
  //SIP could be like:
  // case USD | `$` => USD
  // case PLN | `zl` => PLN
  // case EUR | `e` => EUR
  // case _ => throw new Exception("Wrong currency symbol")

  case class CurrencyConverter(conversion: Map[(Currency, Currency), BigDecimal]) {
    def convert(from: Currency, to: Currency) : BigDecimal= {
      if (from == to) 1
      else conversion((from, to))
    }
    //SIP May get here java.util.NoSuchElementException
  }

  implicit def catchdouble(number: Double) = new NumberToMoney(number)

  class NumberToMoney(amount: BigDecimal){
    def apply(currencySymbol: Currency)(implicit currencyConverter: CurrencyConverter) = Money(amount, convertCurrencySymbol(currencySymbol))
  }


}
