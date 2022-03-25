package money
import MoneyOperations.{Currency, CurrencyConverter, convertCurrencySymbol}

case class Money(amount: BigDecimal, currency: Currency) (implicit CurrencyConverter: CurrencyConverter) {
  //SIP could have some private method here to have less boilerplate, like
  //def convert(that: Money) = that.amount * CurrencyConverter.convert(that.currency, currency)
  def +(that: Money): Money = Money(amount + (that.amount * CurrencyConverter.convert(that.currency, currency)), currency)
  def -(that: Money): Money = Money(amount - (that.amount * CurrencyConverter.convert(that.currency, currency)), currency)
  def *(multiplier: Int): Money = Money(amount * multiplier, currency)
  def >(that: Money):Boolean = (amount > (that.amount * CurrencyConverter.convert(that.currency, currency)))
  def <(that: Money):Boolean = (amount < (that.amount * CurrencyConverter.convert(that.currency, currency)))

  def as(thatCurrency: Currency): Money = {
    val rate = CurrencyConverter.convert(currency, convertCurrencySymbol(thatCurrency))
    Money(amount * rate, thatCurrency)
  }

  override def toString: String = s"${amount} ${currency.toString}"
}

