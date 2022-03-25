import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global
import java.util.Calendar
import java.io.FileWriter

object LogOperations {

  def writeToLogFile (LogContent:String, logFileLocation:String) : Future[Unit] = Future {
    val writer = new FileWriter(logFileLocation, true)
    val dT = Calendar.getInstance()
    writer.write(LogContent + " " + dT.getTime() + "\n" )
    writer.close()
  }
}
