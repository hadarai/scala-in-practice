package utilities

object Utils { //implementing methods:
  def unSafe[T](ex: Exception)(f: => T) = {
    try f
    catch {
      case ex: Exception => println("exception caught: " + ex);
    }
  }
  //catch any exception, log the error & throw the ex exception instead

  def isSorted(as: List[Int], ordering: (Int, Int) => Boolean): Boolean = {
    if (as.tail.isEmpty) true
    else {
      if (ordering(as.head, (as.tail).head)) isSorted(as.tail, ordering)
      else false
    }
  }
// SIP above is correct, but could be also done with pattern matching, like:
//  def isSorted(as: List[Int], ordering: (Int, Int) => Boolean): Boolean = {
//         as match {
//             case h1 :: (h2 :: t) => ordering(h1, h2) && isSorted(h2 :: t, ordering)
//             case _ => true
//         }
//     }

  //checks if as is sorted according to ordering

  def isAscSorted(as: List[Int]) = isSorted(as, (a: Int, b: Int) => a < b)
  //checks if as is sorted in ascending order
  def isDescSorted(as: List[Int]) = isSorted(as, (a: Int, b: Int) => a > b)
  //checks if as is sorted in descending order
  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = {
    if (l.isEmpty) z
    else foldLeft(l.tail, f(z, l.head))(f)
  }
  // applies a binary operator to a start value and all elements of l, going left to right.
  // Don't use build-in List.foldLeft


  def sum(l: List[Int]) = foldLeft(l, 0)((a: Int, b: Int) => a + b)

  //sum elements of l with usage of foldLeft function
  def length[A](l: List[A]) = foldLeft(l, 0)((len: Int, _) => len + 1)
  //length of l with usage of foldLeft function

  def compose[A, B, C](f: A => B, g: B => C) = { x: A => g(f(x)) }
  //compose two unary functions:compose(f,g)(x) = f(g(x)). Don't use Function1.compose2

  def repeated[A](f: A => A, n: Int): A => A = {
    // takes unary function f with an integer n & returns the n-th repeated application of the function.
    // For example: repeated(f, 3)(x) = f(f(f(x)))

    // I decided to write my own repeated, a little more sophisticated - more general
    // It takes f, n and makes f(f(f...(f(x))...))) - composes f exactly n times and returns a function
    if (n == 0) f
    else {
      if (n % 2 == 0) repeated((x => f(f(x: A))): A => A, n / 2)
      else repeated((x => f(x: A)): A => A, n - 1)
    }
  }
  //SIP nice. Also some arguments validation should be here, like: require(n >= 0, "$n must be >= 10")

  def curry[A, B, C](f: (A, B) => C) =  (x: A) => (y: B) => (f(x, y))
  // converts a binary function f of two arguments into a function of one argument that partially applies f.
  // For example, when def add(a: Int, b: Int) = a + b, than curry(add)(1)(1) == add(1, 1)


  def uncurry[A, B, C](f: A => B => C) = (a: A, b: B) => (f(a)) (b)
  //reverse of curry function. For example, uncurry(f)(1, 1)(1) == f(1)(1)
}