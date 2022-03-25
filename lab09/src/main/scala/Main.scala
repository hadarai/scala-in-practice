import Blackjack.Blackjack
import Deck._

object Main {
  def main(args: Array[String]) = {

    val exampleDeck2 = Deck.apply()

    val exampleGame = new Blackjack(exampleDeck2)

    exampleGame.play(10)
    exampleGame.play(52)

    val exampleBlackDeck = Blackjack.apply(3)
    println("Proof of working Blackjack.apply using amountWithFace on generated deck:")
    println(exampleBlackDeck.blackjackDeck.amountWithFace)

  }
}
