import Deck._
import org.scalatest.funsuite.AnyFunSuite

class DeckTest extends AnyFunSuite {
  val exampleCard = Card(hearts, queen)

  test("Card.card") {
    assert(exampleCard.toString === "Card(hearts,queen)")
  }

  val exampleDeck1 = new Deck(List(
    exampleCard,
    Card(hearts, two),
    Card(club, king),
    Card(club, king),
    Card(hearts, three),
    Card(diamond, seven),
    Card(diamond, seven),
    Card(diamond, seven),
    Card(spades, ten)))

  val exampleDeck2 = Deck.apply()

  test("Deck.isStandard") {
    assert(exampleDeck1.isStandard === false)
    assert(exampleDeck2.isStandard === true)
  }

  test("Deck.duplicatesOfCard") {
    assert(exampleDeck1.duplicatesOfCard(Card(diamond, seven)) === 3)
    assert(exampleDeck2.duplicatesOfCard(Card(diamond, seven)) === 1)
  }

  test("Deck.amountOfColor") {
    assert(exampleDeck1.amountOfColor(hearts) === 3)
  }
  test("Deck.amountOfNumerical") {
    assert(exampleDeck2.amountOfNumerical(seven) === 4)
  }
  test("Deck.amountWithNumerical") {
    assert(exampleDeck2.amountWithNumerical === 36)
  }
  test("Deck.amountOfFace") {
    assert(exampleDeck1.amountOfFace(king) === 2)
    assert(exampleDeck2.amountOfFace(king) === 4)
  }
  test("Deck.amountWithFace") {
    assert(exampleDeck1.amountWithFace === 3)
    assert(exampleDeck2.amountWithFace === 16)
  }
}