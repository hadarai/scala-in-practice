package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import sign_in_system.DB

@Singleton
class StudentController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  def index = Action {
    val list_of_student_names = for (x <- DB.test_Students_list) yield x.name
    val all_students = (list_of_student_names.toString())
      .replace(", ","\n")
      .replace("List(", "")
      .replace(")","")
    val final_show = "List of all students in our institute:\n\n" + all_students
    Ok(final_show)
  }

  def by_id(id:String) = Action {
    val student_found = DB.test_Students(id.toInt)
    val final_show = "Student you're searching for:\n\n" + "Index: " + student_found.index + "\n Name: " +
      student_found.name + "\n Year: " + student_found.year
    Ok(final_show)
  }
}