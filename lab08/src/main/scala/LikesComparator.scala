import com.restfb.types.User
import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.util.{Success, Failure}

object LikesComparator {
  def compareLikes(logFile: String, user1: String, user2: String): Unit = {
    val accessToken = "EAADi6dVwTQ4BAOZADU06VwkAGHJ8k8LSG0n0ENab5X7XOCwVfWx4ekpSX9KkQ9lzndNxflU9L0LDhFemNv1wszmC8ZCyBqosGdI7zYmJPxNLYHXBgSHZAiKKHEDrf9IfV5ezQBwXHCxmKboJ5Cqm5dwZBRWKZBfQdaKHdCWLfbR4LycXbZBUiSq2dBnW01MmbIZAcsPslrFRwZDZD"

    val user1Struct: Future[User] = FacebookAdapter.getUser(accessToken, user1)
    val user2Struct: Future[User] = FacebookAdapter.getUser(accessToken, user2)

    Await.result(user2Struct, Duration.Inf)
    Await.result(user1Struct, Duration.Inf)
    //SIP  may user1Struct zip user2Struct to have just one future

    val result = for {
      r1 <- user1Struct
      r2 <- user2Struct
    } yield (r1, r2)

    result.onComplete {
      case Success(x) => {
        val loggingResult = LogOperations.writeToLogFile(x._1.getFirstName +" "+ x._2.getFirstName, logFile)
        println(x._1.getFirstName + ", likes: " + x._1.getLikes.getTotalCount +" vs\n" + x._2.getFirstName+", likes: "+ x._2.getLikes.getTotalCount)
        Await.result(loggingResult, Duration.Inf)
      }
      case Failure(e) => e.printStackTrace
    }
  }
}


//All computations should run in parallel:
// 1. Get user with id=user1
// 2. Get user with id=user2
// 3. Extend file (or create new file if not exist) in path=logFile with line: $CURRENT_TIME $user1 $user2
// 4. Print on screen: ${user1.name}, likes: ${user1.getLikes.getTotalCount} vs.
//                     ${user2.name}, likes: ${user2.getLikes.getTotalCount}
