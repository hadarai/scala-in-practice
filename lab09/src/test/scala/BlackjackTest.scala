import Blackjack._
import Deck._
import org.scalatest.funsuite.AnyFunSuite

class BlackjackTest extends AnyFunSuite {
  val exampleGame = new Blackjack(Deck.apply())

  val exampleBlackDeck = Blackjack.apply(3)

  test("Blackjack.amountWithFace") {
    assert(exampleBlackDeck.blackjackDeck.amountWithFace === 48)
  }

  test("Blackjack.points") {
    assert(exampleGame.countPoints(1) === 340)
    assert(exampleGame.countPoints(11) === 380)
  }

}