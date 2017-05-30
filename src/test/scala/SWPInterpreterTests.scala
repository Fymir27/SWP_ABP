import org.scalatest.FunSuite
import scala.util.parsing.combinator._

class SWPInterpreterTests extends FunSuite {

  test("Parser minimal example") {
    val prog = """
    42
    """
    if(SWPInterpreter.checkProgramGrammer(prog)) {
      assert(true)
    } else {
      fail(SWPInterpreter.checkProgramGrammerStringResult(prog))
    }
  }

  test("Parser short program") {
    val prog = """
    let
      concat = (xs, ys)=> if eq?(xs,[]) then ys else build(first(xs), concat(rest(xs),ys))
    in
      concat([1,2,3],[4,5])
    """
    if(SWPInterpreter.checkProgramGrammer(prog)) {
      assert(true)
    } else {
      fail(SWPInterpreter.checkProgramGrammerStringResult(prog))
    }
  }

  test("Parser defect program") {
    val prog = """
    let
      concat = (xs, ys)=> if eq?(xs,[] then ys else build(first(xs), concat(rest(xs),ys))
    in
      concat([1,2,3],[4,5])
    """
    assert(! SWPInterpreter.checkProgramGrammer(prog))
  }

  test("Interpreter program with only build in functions") {
    val prog = """
    if eq?([1],build(1,[])) then add(3, negate(1)) else add(4,2)
    """
    assertResult(Right(ExpInteger(2))){
      SWPInterpreter.evaluateProgram(prog)
    }
  }


  test("Interpreter short program") {
    val prog = """
    let
      concat = (xs, ys)=> if eq?(xs,[]) then ys else build(first(xs), concat(rest(xs),ys))
    in
      concat([1,2,3],[4,5])
    """
    assertResult(Right(ExpList(List(1,2,3,4,5).map(ExpInteger)))){
      SWPInterpreter.evaluateProgram(prog)
    }
  }

  test("Interpreter let expr") {
    val prog = """
    let
      a = 10;
      b = add(a,22)
    in
      add(a,b)
    """
    assertResult(Right(ExpInteger(42))){
      SWPInterpreter.evaluateProgram(prog)
    }
  }

  test("Interpreter even/odd") {

    val prog = """
    let
      even? = (n) => if eq?(n, 0) then True else odd?(add(n, negate(1)));
      odd? = (n) => if eq?(n, 0) then False else even?(add(n, negate(1)))
    in
      even?(10)
    """

    assertResult(Right(ExpBoolean(true))){
      SWPInterpreter.evaluateProgram(prog)
    }
  }

  test("Interpreter list comprehensions") {

    val prog = """
    [add(x,x) | x <- [1,2,3]]
    """

    assertResult(Right(ExpList(List(2,4,6).map(ExpInteger)))){
      SWPInterpreter.evaluateProgram(prog)
    }
  }




}
