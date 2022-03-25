package figures
import numbers.Rational
import math.sqrt

abstract class Figure () {
  def area: Double
  val description: String
  override def toString = description + ", size : " + area
}

class Point(x: Rational, y: Rational) extends Figure {
  def this (x:Int, y:Int) = this (Rational(x), Rational(y))
  def this (x:Int, y:Rational) = this (Rational(x), y)
  def this (x:Rational, y:Int) = this (x, Rational(y))
  val X = x
  val Y = y
  def dist(other:Point): Double = {
    sqrt(((other.X - X) * (other.X - X) + (other.Y - Y) * (other.Y - Y)).toDouble)
  }
  override def area: Double = 0
  override val description :String = "Point"
}
object Point {
  def origin = new Point (0, 0)
}


class Triangle(a:Point, b:Point, c:Point) extends Figure {
  require(!((a.X == b.X && a.Y == b.Y) || (a.X == c.X && a.Y == c.Y) || (b.X == c.X && b.Y == c.Y)), "At least two identical points to build triangle")
  require(!(((a.X * (b.Y - c.Y)) + (b.X * (c.Y - a.Y)) + (c.X * (a.Y - b.Y))) == Rational.zero), "Points lay on one line")
  val A = a
  val B = b
  val C = c
  def area: Double = {
    (Rational(1,2) * (((A.X * B.Y) + (B.X * C.Y) + (C.X * A.Y)) - ((C.X * B.Y) + (A.X * C.Y) + (B.X * A.Y)))).toDouble().abs
  }
  require(area != 0, "Area is equal to zero anyways, so something's wrong with given points!")
  val description: String = "Triangle"
}
object Triangle {
  def simple (x:Rational) = new Triangle(Point.origin, new Point(Rational(2)*x, 0), new Point(x, x * Rational(2)))
  def simple (x:Int) = new Triangle(Point.origin, new Point(2*x, 0), new Point(x, x * 2))
}

class Rectangle(a:Point, b:Point, c:Point, d:Point) extends Figure {
  val A = a
  val B = b
  val C = c
  val D = d
  require(A.dist((B)) == C.dist(D) && A.dist((C)) == B.dist(D), "Rectangle has side pairs of equal lengths")
  val triangle1 = new Triangle(A, B, C)
  val triangle2 = new Triangle(B, C, D)
  override def area: Double = triangle1.area + triangle2.area
  override val description: String = "Rectangle"
}
object Rectangle {
  def simple(x:Rational, y:Rational) = new Rectangle(Point.origin, new Point(0, x), new Point(y, 0), new Point(x, y))
  def simple(x:Rational, y:Int) = new Rectangle(Point.origin, new Point(0, x), new Point(y, 0), new Point(x, y))
  def simple(x:Int, y:Rational) = new Rectangle(Point.origin, new Point(0, x), new Point(y, 0), new Point(x, y))
  def simple(x:Int, y:Int) = new Rectangle(Point.origin, new Point(0, x), new Point(y, 0), new Point(x, y))

  // This is ugly so I wanted to do this:
  // def simple(x: Either[Rational, Int], y: Either[Rational, Int]) = new Rectangle(Point.origin, new Point(0, x), new Point(y, 0), new Point(x, y))
  // but it doesnt work

  // SIP Great that you know about Either monad. In that case invocation of this method would be like:
  // simple(Left(Rational(...), Left(Rational(...))) or simple(Right(SOME_INT), Left(Rational(...))) or ...
  // also because signature of Point is on Rational and not on Either - you will need to do pattern mathching somewhere, like:
  // simple(x: Either[Rational, Int], y: Either[Rational, Int]) = (x, y) match {
  //  case (Left(r1), Left(r2)) =>  new Rectangle(Point.origin, new Point(0, r1), new Point(r2, 0), new Point(r1, r2))
  //  case ...
  //    . . .
  //}
}

class Square(a:Point, b:Point, c:Point, d:Point) extends Rectangle(a, b, c, d) {
  require(A.dist((B)) == C.dist(D) && C.dist(D) == A.dist((C)) && A.dist((C)) == B.dist(D), "Square has sides of equal lengths")
  override val description: String = "Square"
}
object Square {
  def simple(x:Rational) = new Square(Point.origin, new Point(0, x), new Point(x, 0), new Point(x, x))
  def simple(x:Int) = new Square(Point.origin, new Point(0, x), new Point(x, 0), new Point(x, x))
  //This is ugly so I wanted to do this:
  //def simple(x: Either[Rational, Int]) = new Square(Point.origin, new Point(0, x), new Point(x, 0), new Point(x, x))
  //but it doesnt work
}