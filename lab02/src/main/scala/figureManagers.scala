import figures.Figure
package figureManagers {
  object figureManager {

    def areaSum(figures: List[Figure]): Double = figures.fold (0) ((acc, fig) => acc + fig.area)

    // var areaSum: Double = 0

    // def areaSum(figures: List[Figure]): Double = {
    //   for (x <- figures) areaSum += x.area
    //   areaSum
    // } //Sum all areas

    def printAll(figures: List[Figure]): Unit = {
      for (x <- figures)
        println(x)
    } //Print all descriptions
  }

}