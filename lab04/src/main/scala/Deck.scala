import cards._

package deck {

  import scala.collection.BuildFrom
  import scala.util.Random

  case class Deck(cards: List[Card] ) {
    val deckOfCards : List[Card] = cards

    def pull() = new Deck(deckOfCards.tail)
    //creates new deck without first card

    def push(c: Card) = new Deck(c :: deckOfCards)
    //creates new deck with given card pushed on top
    def push(color: Color, value: Value) = new Deck(Card(color, value) :: deckOfCards)
    //creates new deck with newcard(color, value) pushed on top

    def duplicatesOfCard(card: Card): Int = deckOfCards.count(_ == card)
    //amount of duplicates of the given card in the deck

    def amountOfColor(color: Color): Int = deckOfCards.count(_.c == color)
    //amount of cards in the deck for the given color

    def amountOfNumerical(numerical: Numerical): Int = deckOfCards.count(_.v == numerical)
    //amount of cards in the deck for given numerical card (2, 3, 4, 5, 6, 7, 8, 9, 10)
    val amountWithNumerical: Int = deckOfCards.count(_.v.isInstanceOf[Numerical])
    //amount of all numerical cards in the deck (2, 3, 4, 5, 6, 7, 8, 9, 10)

    def amountOfFace(face: Face) : Int = deckOfCards.count(_.v == face)
    //amount of cards inthe deck for the given face (Jack, Queen & King)
    val amountWithFace: Int = deckOfCards.count(_.v.isInstanceOf[Face])
    //amount of all cards in the deck with faces (Jack, Queen & King)

    val isStandard: Boolean = {
      //SIP dont use 'magic numbers' (here: 52, 16...), instead encapsulate it in a val
      //Also it could be done easier... check if 52 cards are in deck & deck is distinct (contains no duplicates)
      //Check for example: def distinct: Seq[A], from https://www.scala-lang.org/api/current/scala/collection/Seq.html
      deckOfCards.size == 52 &&_

      amountWithNumerical == 36 &&_
      amountWithFace == 16 &&_

      amountOfColor(club) == 13 &&_
      amountOfColor(diamond) == 13 &&_
      amountOfColor(hearts) == 13 &&_
      amountOfColor(spades) == 13 &&_

      amountOfNumerical(two) == 4 &&_
      amountOfNumerical(three) == 4 &&_
      amountOfNumerical(four) == 4 &&_
      amountOfNumerical(five) == 4 &&_
      amountOfNumerical(six) == 4 &&_
      amountOfNumerical(seven) == 4 &&_
      amountOfNumerical(eight) == 4 &&_
      amountOfNumerical(nine) == 4 &&_
      amountOfNumerical(ten) == 4
    }
    // checks if deck is a standard deck
  }





object Deck {
  def apply() = new Deck(Random.shuffle(for {
    c <- List(club, diamond, hearts, spades)
    v <- List(two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace)
  } yield Card(c, v)))
  //creates the standard deck with random order of cards. Check Random.shuffle function
  }
}