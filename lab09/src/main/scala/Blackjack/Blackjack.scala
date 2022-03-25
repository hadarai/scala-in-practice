package Blackjack

import Deck._

import scala.util.Random

class Blackjack(deck: Deck) {
  val blackjackDeck: Deck = deck
  val sizeOfDeck: Int = blackjackDeck.deckOfCards.size
  // Points calculation:
  // 1. Numerical cards as their numerical value = 2 - 10.
  // 2. Face cards (Jack, Queen, King) = 10
  // 3. Ace = 1 or 11 (player could choose)

  def play(n: Int): Unit = {
    println("You have:")
    var sumMax = 0
    var sumMin = 0

    for (i <- 0 until n) {
      printf("%d. ", i + 1)
      val currentCard = blackjackDeck.deckOfCards(i)
      if (currentCard.value == ace) {

        sumMax += 11
        sumMin += 1
        println(currentCard.toString + " worth 1 or 11 points, you choose :-) ")
      } else {
        val points = currentCard.value match {
          case _: two.type   => 2
          case _: three.type => 3
          case _: four.type  => 4
          case _: five.type  => 5
          case _: six.type   => 6
          case _: seven.type => 7
          case _: eight.type => 8
          case _: nine.type  => 9
          case _: ten.type   => 10
          case _             => 10
        }

        //SIP, pattern-matching gives a value (not like in Java, where switch-case is a statement),
        // Could be like:
        // val points = currentCard.value match {
        //   case _: two.type =>  2
        //   case _: three.type =>  3
        //   case _: four.type =>  4
        //   case _: five.type =>  5
        //   case _: six.type => 6
        //   case _: seven.type =>  7
        //   case _: eight.type =>  8
        //   case _: nine.type =>  9
        //   case _: ten.type =>  10
        //   case _: ten.type |_: jack.type | _: king.type => 10
        // }
        //but still will be cleaner to have common abstraction for: two... ten, like:
        //sealed abstact class Numerical(val points: int }
        //case object two extends Numerical(2)
        //case object three extends Numerical(3)
        //...
        //Than in PM we will have:
        //  val points = currentCard.value match {
        //   case Numerical(_) => _
        //   ...
        //  }

        sumMax += points
        sumMin += points
        printf(currentCard.toString + " worth %d points.\n", points)
      }
    }
    printf("Maximum of avaliable points = %d, and minumim = %d\n", sumMax, sumMin)
  }
  // loop taking (first?) n cards from the deck,
  // pretty-printing them with points & printing the sum of points on the end

  //  private def points1(groupOfCards: List[Card]): Int = {
  //    var sumOfPoints = 0
  //    for (el <- groupOfCards) {
  //      //      println(el.value)
  //      el.value match {
  //        case _: two.type   => sumOfPoints += 2
  //        case _: three.type => sumOfPoints += 3
  //        case _: four.type  => sumOfPoints += 4
  //        case _: five.type  => sumOfPoints += 5
  //        case _: six.type   => sumOfPoints += 6
  //        case _: seven.type => sumOfPoints += 7
  //        case _: eight.type => sumOfPoints += 8
  //        case _: nine.type  => sumOfPoints += 9
  //        case _: ten.type   => sumOfPoints += 10
  //        case _: jack.type  => sumOfPoints += 10
  //        case _: queen.type => sumOfPoints += 10
  //        case _: king.type  => sumOfPoints += 10
  //        case _: ace.type   => sumOfPoints += 1
  //      }
  //    }
  //    sumOfPoints
  //  }
  private def points(groupOfCards: List[Card], valueOfAce: Int): Int = {
    var sumOfPoints = 0
    for (el <- groupOfCards) {
      el.value match {
        case _: two.type   => sumOfPoints += 2
        case _: three.type => sumOfPoints += 3
        case _: four.type  => sumOfPoints += 4
        case _: five.type  => sumOfPoints += 5
        case _: six.type   => sumOfPoints += 6
        case _: seven.type => sumOfPoints += 7
        case _: eight.type => sumOfPoints += 8
        case _: nine.type  => sumOfPoints += 9
        case _: ten.type   => sumOfPoints += 10
        case _: jack.type  => sumOfPoints += 10
        case _: queen.type => sumOfPoints += 10
        case _: king.type  => sumOfPoints += 10
        case _: ace.type   => sumOfPoints += valueOfAce
      }
    }
    sumOfPoints
  }

  def countPoints(valueOfAce: Int): Int = {
    points(blackjackDeck.cards, valueOfAce)
  }

  lazy val all21: List[List[Card]] = {
    for (amountOfCardsInCombination <- List.range(2, sizeOfDeck)) yield {
      (for {
        cardCombination <- blackjackDeck.deckOfCards.combinations(amountOfCardsInCombination)
        if points(cardCombination, 1) == 21 || points(cardCombination, 11) == 21
      } yield {
        cardCombination
      }).toList.flatten
    }
  }
  // finds all subsequences of cards which could give 21 points

  def first21(): Unit = {
    all21.head
    ()
  }
  // finds and pretty-prints the first subsequence of cards which could give 21 points
  // I assume that we won't
}

object Blackjack {
  def apply(numOfDecks: Int): Blackjack = {
    val listOfListsOfCards =
      for (_ <- List.range(0, numOfDecks)) yield {
        Random.shuffle(
          for {
            c <- List(club, diamond, hearts, spades)
            v <- List(two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace)
          } yield Card(c, v))
      }
    new Blackjack(new Deck(listOfListsOfCards.flatten))
  }
  // creates Blackjack game having numOfDecks-amount of standard decs with random order of cards.
  // For example, with Blackjack(3) deck would have 156 cards
}

