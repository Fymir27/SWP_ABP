sealed trait Expression
//TODO Add case classes to represent the Expression AST
case class ApplyExp(funcName : String, paramters : List[Expression]) extends Expression
case class FunExp() extends Expression
case class CondExp(condition : Expression, _then : Expression, _else : Expression) extends Expression
case class IdExp(s : String) extends Expression
case class BoolExp(value : Boolean) extends Expression