package calculus
import numbers.Rational
import figureManagers.figureManager
// Creating object
object Main
{
  def rationalShowcase(): Unit = {
    val rat1 = new Rational(2, 3)
    val rat2 = new Rational(6, 8)
    val rat3 = new Rational(6, -10)
    val rat4 = new Rational(50, 6)

    println("Rationals showcase:")

    println(rat1 + rat2)
    println(rat1 - rat2)
    println(rat1 * rat2)
    println(rat1 / rat2)
    println(rat3)
    println(rat4)
    println(rat4.toDouble())
  }

  def figuresAndSingletonShowcase(): Unit = {
    import figures._
    println("\nFigues showcase:")

    // Correct triangle
    val point1 = new Point(1, 1)
    val point2 = new Point(Rational(1,4), Rational(8,9))
    val point3 = new Point(Rational(2,3), Rational(5,6))
    val triangle1 = new Triangle(point1, point2, point3)

    //    First triangle requirement broken:
    //    println(new Triangle(point1, point1, point3))

    //    Second triangle requirement broken:
    //    println(new Triangle(new Point(1,1), new Point(1,2), new Point(1, 3)))

    val rectangle1 = new Rectangle(new Point(0,0), new Point(0,7), new Point(20,0), new Point(20,7))
    val square1 = new Square(Point.origin, new Point(0, 5), new Point(5, 0), new Point(5, 5))

    val allFigures = List(triangle1, rectangle1, square1)
    println("Figures area summed: " + figureManager.areaSum(allFigures))
    figureManager.printAll(allFigures)
  }

  def main(args: Array[String])
  {
    rationalShowcase()
    figuresAndSingletonShowcase()
  }
}
