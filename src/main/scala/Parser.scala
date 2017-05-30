import scala.util.parsing.combinator._

class ExpParser extends JavaTokenParsers {

  def expression : Parser[Expression] = apply_expr | cond_expr | let_expr | int | list | bool | id
  def apply_expr : Parser[Expression] = id | "("~expression~")"
  def fun_expr : Parser[Expression]
  def cond_expr : Parser[Expression]
  def let_expr : Parser[Expression]
  def binding : Parser[Expression] = id~"="~expression
  def int : Parser[Expression]
  def list : Parser[Expression]
  def bool : Parser[Expression]
  def id : Parser[Expression]
  //TODO Implement the Expression Parser and add additional parsers for terminal and non terminal symbols, where necessary

}

object ParseProgram extends ExpParser {
  def parse(s: String): ParseResult[Expression] = {
    parseAll(expression, s)
  }
}
