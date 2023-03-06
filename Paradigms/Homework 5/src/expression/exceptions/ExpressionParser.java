package expression.exceptions;

import expression.AlgebraicExpression;
import expression.Const;
import expression.Variable;
import expression.base.Priority;
import expression.parser.GenericParser;

import java.util.Map;
import java.util.Set;

public class ExpressionParser extends GenericParser<AlgebraicExpression> implements TripleParser {
    public ExpressionParser() {
        super(Map.of(
                "+", new BinaryOpUnit<>(Priority.SUM, CheckedAdd::new),
                "-", new BinaryOpUnit<>(Priority.SUM, CheckedSubtract::new),
                "*", new BinaryOpUnit<>(Priority.MULTIPLY, CheckedMultiply::new),
                "/", new BinaryOpUnit<>(Priority.DIVIDE, CheckedDivide::new),
                "gcd", new BinaryOpUnit<>(Priority.GCD, CheckedGCD::new),
                "lcm", new BinaryOpUnit<>(Priority.LCM, CheckedLCM::new)
        ), Map.of(
                "-", CheckedNegate::new,
                "reverse", CheckedReverse::new,
                "pow10", CheckedPow10::new,
                "log10", CheckedLog10::new
        ), Set.of("x", "y", "z"),
                string -> new Const(Integer.parseInt(string)),
                Variable::new);
    }
}
