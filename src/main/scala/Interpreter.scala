object Interpreter {

  //Type aliases to make the signatures more readable
  type VariableName = String
  type Environment =  Map[VariableName, ExpValue]

  val builtinFunctions : Map[VariableName ,List[ExpValue] => ExpValue] =
    Map(
      "not" -> {case List(ExpBoolean(b)) => ExpBoolean(!b)},
      "add" -> {case List(ExpInteger(a), ExpInteger(b)) => ExpInteger(a+b)}

      // TODO
      // Implement the other built in functions here.
    )

  // How to use the builtinFunctions Map:
  // The call
  // builtinFunctions("not")(List(ExpBoolean(true)))
  // will return
  // ExpBoolean(false)

  private val builtinFunctionNames = builtinFunctions.keys.toList
  // It is not required to use our datastructure to implement built in functions,
  // you are also allowed to implement it in your own way.


  def interpret(environment: Environment,
                expression: Expression): ExpValue = (Expression) match {
    case BoolExp(b) => ExpBoolean(b)
    case IntExp(i) => ExpBoolean(i)
  }
  //TODO
  //This function should evaluate the given expression, using the environment(omega),
  //containing both functions and values.
  //
  //It might be helpful to define helper functions to evaluate different expressions,
  //like builtins or user defined functions.


}
