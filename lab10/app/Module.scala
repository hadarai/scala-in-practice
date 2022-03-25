import com.google.inject.AbstractModule
import java.time.Clock

//import services.{ApplicationTimer, AtomicCounter, Counter}

  /**
   * This class is a Guice module that tells Guice how to bind several
   * different types. This Guice module is created when the Play
   * application starts.
   *
   * Play will automatically use any class called `Module` that is in
   * the root package. You can create modules in other locations by
   * adding `play.modules.enabled` settings to the `application.conf`
   * configuration file.
   */
class Module extends AbstractModule {

//  override def configure() = {
//    // Use the system clock as the default implementation of Clock
//    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
//    // Ask Guice to create an instance of ApplicationTimer when the
//    // application starts.
//    bind(classOf[ApplicationTimer]).asEagerSingleton()
//    // Set AtomicCounter as the implementation for Counter.
//    bind(classOf[Counter]).to(classOf[AtomicCounter])
//  }
}
package sign_in_system {

  case class Student(index: Int, name: String, year: Short)

  case class Lecture(id: String, name: String) //e.g. Lecture("SIP", "Scala in Practice")
  case class Enrollment(lecture_id: String, students: List[Student])

  object DB {
    val name_list = List("Pawel", "Maciej", "Jan", "Karolina", "Martyna", "Przemyslaw", "Adam", "Ewa", "Kasia")
    var index = 0
    var test_Students = Map(index -> Student(index, "Maksymilian", 1))
    for (name <- name_list) {
      index = index + 1
      test_Students += (index -> Student(index, name, ((index / 4).ceil + 1).toShort))
//      Student(index, name, ((index / 4).ceil + 1).toShort)
    }
    val test_Students_list = (test_Students.toList).map{case (_, s:Student) => s}

    val test_Lectures = Map(
      "sip" -> Lecture("SIP", "Scala in Practice"),
      "aisd" -> Lecture("AiSD", "Algorytmy i Struktury Danych"),
      "md" -> Lecture("MD", "Matematyka Dyskretna"),
      "ask" -> Lecture("ASK", "Architektury Systemow Komputerowych"),
      "jfizo" -> Lecture("JFiZO", "Jezyki Formalne i Zlozonosc Obliczeniowa"),
      "jfiizo" -> Lecture("JFIiZO", "Jezyki Fizjoterapeltyczno-Infantylne i Zlozone Opinie")
    )
    val test_Lectures_list = test_Lectures.toList

//    ((k:Int, v:Student) => (k,v))
    val test_Enrollments = Map(
      "sip" -> Enrollment("SiP", test_Students_list.take(6)),
      "aisd" -> Enrollment("AiSD", test_Students_list.take(8)),
      "md" -> Enrollment("MD", test_Students_list.take(4)),
      "ask" -> Enrollment("ASK", test_Students_list.take(3)),
      "jfizo" -> Enrollment("JFiZO", test_Students_list.drop(7)),
      "jfiizo" -> Enrollment("JFIiZO", test_Students_list)
    )
    // hard-code at least 10 students, 4 lectures & couple enrollments
  }

}