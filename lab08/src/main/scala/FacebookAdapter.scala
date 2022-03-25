import com.restfb.DefaultFacebookClient
import com.restfb.FacebookClient
import com.restfb.Parameter
import com.restfb.exception.FacebookException
import com.restfb.types.FacebookType
import com.restfb.types.Page
import com.restfb.types.User
import com.restfb.Version

import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global

object FacebookAdapter {
  private val myAppSecret= "xyz"  //any String (or Note1)
  class MyFacebookClient(currentAccessToken: String)
   extends DefaultFacebookClient(
     currentAccessToken,
//     myAppSecret,
     Version.VERSION_5_0) {
 }

  //SIP User object retrieved from FB is quite big & has many fields not needed for your app.
  //Would be beter to have new class (like MyUser) with only couple fields and return its instance in this method:
  //def getUser(accessToken: String, id: String): Future[MyUser]
  def getUser(accessToken: String, id: String) :Future[User] = Future {
    val client = new MyFacebookClient(accessToken)
    val user = client.fetchObject(id, classOf[User], Parameter.`with`("fields", "name, email, gender, id, birthday, likes"))
    user
  }
}