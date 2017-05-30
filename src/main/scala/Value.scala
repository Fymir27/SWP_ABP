
//DO NOT CHANGE!
//This datatypes will be used to test your solution!
sealed trait ExpValue
case class ExpInteger(i : Int) extends ExpValue
case class ExpList(xs : List[ExpValue]) extends ExpValue
case class ExpFunction(parameters: List[String], body: Expression) extends ExpValue
case class ExpBoolean(b:Boolean) extends ExpValue

object PrettyPrinter {

  // You can use this function to produce readable output.
  // There is no required format; we will use ExpValue to test your solution.
  def print(value: ExpValue):String = value match {
    case ExpBoolean(true) => "True"
    case ExpBoolean(false) => "False"
    case ExpInteger(i) => i.toString
    case ExpList(xs) => xs.map(print).mkString("[",",","]")
    case ExpFunction(parameters, body) => parameters.mkString("(",",",")") + " => " + body
  }

}
