import java.util.Base64

import cards._
import deck._
import games._

object Main {
  def main(args: Array[String]) {
    println("\nExample card:")
    val exampleCard = Card(hearts, queen)
    println(exampleCard)

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

    println("\nExample deck 1:")
    println(exampleDeck1.toString)
    println("Example deck 2 (standard):")
    println(exampleDeck2.toString)

    println("\nIs deck 1 standard:")
    println(exampleDeck1.isStandard)
    println("Is deck 2 standard:")
    println(exampleDeck2.isStandard)

    println("\nDuplicates of Diamond Seven in deck 1:")
    println(exampleDeck1.duplicatesOfCard(Card(diamond, seven)))
    println("Duplicates of Diamond Seven in deck 2:")
    println(exampleDeck2.duplicatesOfCard(Card(diamond, seven)))

    println("\namountOfColor in deck 1:")
    println(exampleDeck1.amountOfColor(hearts))

    println("\namountOfNumerical in deck 2:")
    println(exampleDeck2.amountOfNumerical(seven))
    println("amountWithNumerical in deck 1:")
    println(exampleDeck1.amountWithNumerical)

    println("\namountOfFace - King in deck 1:")
    println(exampleDeck1.amountOfFace(king))
    println("amountOfFace - King in deck 2:")
    println(exampleDeck2.amountOfFace(king))

    println("\namountWithFace in deck 1:")
    println(exampleDeck1.amountWithFace)
    println("amountWithFace in deck 2:")
    println(exampleDeck2.amountWithFace)


    val exampleGame = new Blackjack(exampleDeck2)

    exampleGame.play(10)
    exampleGame.play(52)

    val exampleBlackDeck = Blackjack.apply(3)
    println("Proof of working Blackjack.apply using amountWithFace on generated deck:")
    println(exampleBlackDeck.blackjackDeck.amountWithFace)

//    This is not working. I run out of time to fix it.
//    println("Warning! This code may explode:")
//    println(exampleBlackDeck.first21())
//    println(((exampleBlackDeck.all21).toList)(2))
  }
}
