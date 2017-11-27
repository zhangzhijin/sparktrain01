package scala_grammar

object scala_bibao {

  var factor = 3
  val multiplier = (i: Int) => i * factor;

  def main(args: Array[String]): Unit = {

    println(multiplier(1));

    println(multiplier(2));

  }

}