import plugins._
object Actions {
  val actionA: Pluginable = new Pluginable with Shortening with Doubling with SingleSpacing
  //plugin applying plugins with order:SingleSpacing => Doubling => Shortening
  val actionB: Pluginable = new Pluginable with Doubling with Shortening with NoSpacing
  //plugin applying plugins with order: NoSpacing => Shortening => Doubling
  val actionC: Pluginable = new Pluginable with Doubling with LowerCasing
  //plugin applying plugins with order: LowerCasing => Doubling
  val actionD: Pluginable = new Pluginable with Rotating with DuplicateRemoval
  //plugin applying plugins with order: DuplicateRemoval => Rotating
  val actionE: Pluginable = new Pluginable with Reverting with Doubling with Shortening with NoSpacing
  // plugin applying plugins with order: NoSpacing => Shortening => Doubling => Reverting
  val actionF: Pluginable = new Pluginable with Rotating
  //plugin applying plugin Rotating 5-times◦ i can't
  //SIP this one cant be done ony with Stackable Modifications. Could be like (very generic solution):
  // val actionF: Pluginable = new PluginRepeated(new Pluginable with Rotating, 5)
  // class PluginRepeated(p: Pluginable, repeat: Int) extends Pluginable {
  // //...require for repeat
  //   override def plugin(s: String): String = repeatPlugin(s, repeat)
  //   private def repeatPlugin(s: String, i: Int): String = {
  //     if (i <= 0) s else repeatPlugin(p.plugin(s), i-1)
  //   }
  // }

  
  val actionG: Pluginable = new Pluginable with Doubling with Shortening with NoSpacing with SingleSpacing
  //plugin applying plugins with order: actionA => actionB
  //SIP Its not a good approach. If in the future someone will change the actionA or actionA, will need to remember to
  //change also actionG. Check DRY principle (google 'don't repeat yourself').
}
