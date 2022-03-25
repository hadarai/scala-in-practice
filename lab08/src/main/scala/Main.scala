import com.restfb.types.User
import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.util.{Success, Failure}

object Main {
  def main(args: Array[String]): Unit = {
    val myID = "2970282193057236" //mine - Maksymilian's faceboook ID for that app
    val yourID = ""

    LikesComparator.compareLikes("Logfile.txt", myID, myID)
    LikesComparator.compareLikes("Logfile.txt", myID, yourID)
  }
}