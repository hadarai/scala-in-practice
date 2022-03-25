package cards

sealed trait Color
case object club extends Color
case object diamond extends Color
case object hearts extends Color
case object spades extends Color

sealed trait Value
sealed trait Numerical extends Value
case object two extends Numerical
case object three extends Numerical
case object four extends Numerical
case object five extends Numerical
case object six extends Numerical
case object seven extends Numerical
case object eight extends Numerical
case object nine extends Numerical
case object ten extends Numerical

sealed trait Face extends Value
case object jack extends Face
case object queen extends Face
case object king extends Face
case object ace extends Face


case class Card(c: Color, v:Value) {
  val color :Color = c
  val value :Value = v

  //SIP its a case class, client has access to all arguments - val color & val value are redundant

}
