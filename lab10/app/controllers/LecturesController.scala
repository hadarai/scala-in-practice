package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import sign_in_system._

@Singleton
class LecturesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  def index = Action {
//    val list_of_lectures_names = for (x <- DB.test_Students_list) yield x.name
    val all = (DB.test_Lectures.toString())
      .replace("Map(", "")
      .replace(", ","\n")
      .replace("Lecture(","\n   ")
      .replace(")","")
      .replace(",",", ")
    val show = "List of all lectures in our institute:\n\n" + all
    Ok(show)
  }

  def by_id(id:String) = Action {
    val lecture_by_id = DB.test_Lectures(id)
    val list_of_enrolled_student_names = for (x <- DB.test_Enrollments(id).students) yield x.name
    val list_of_enrolled_students = (list_of_enrolled_student_names.toString())
      .replace(", ","\n")
      .replace("List(", "")
      .replace(")","")

    val show = "Lecture you're searching for is:\n\n" + lecture_by_id.name + "\n\nEnrolled students:\n" + list_of_enrolled_students
    Ok(show)
  }
}