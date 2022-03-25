package numbers

class Rational(n: Int, d: Int = 1) {
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val divisor = gcd(n.abs, d.abs)

  //SIP always put arguments validation (require in your case) on top in the class body.
  //Otherwise val divisor = gcd(n.abs, d.abs) will be evaluated also for incorrect arguments
  require(d != 0, "Denominator equal to 0!")
  val nominator = (n / divisor) * d.sign
  val denominator = (d / divisor).abs
  val intigerPart = (nominator/denominator).floor.toInt
  override def toString = {
    if (intigerPart != 0) {
      if ((nominator - intigerPart) != 0) {
        intigerPart + " " + (nominator - denominator * intigerPart) + "/" + denominator
      }
      else intigerPart + " "
    }
    else {
      if (nominator != 0) nominator + "/" + denominator
      else nominator.toString
    }
  }

  def toDouble() = {
    nominator.toDouble / denominator
  }

  def +(other: Rational): Rational = {
    val newN = nominator * other.denominator + other.nominator * denominator
    val newD = denominator * other.denominator
    new Rational(newN, newD)
  } // addition
  def -(other: Rational): Rational = {
    val newN = nominator * other.denominator - other.nominator * denominator
    val newD = denominator * other.denominator
    new Rational(newN, newD)
  } // subtraction
  def *(other: Rational): Rational = {
    val newN = (nominator * other.nominator)
    val newD = (denominator * other.denominator)
    new Rational(newN, newD)
  } // multiplication
  def /(other: Rational): Rational = {
    val newN = (nominator * other.denominator)
    val newD = (denominator * other.nominator)
    new Rational(newN, newD)
  } // division

  def ==(other: Rational): Boolean = nominator == other.nominator && denominator == other.denominator
}
object Rational {
  def invertRational (r: Rational) = new Rational (r.denominator, r.nominator)
  def giveGcd(r: Rational) = r.divisor
  def zero = new Rational(0)
  def one = new Rational(1)
  def apply (n: Int, d: Int) = new Rational (n, d)
  def apply (n: Int) = new Rational (n)
}
