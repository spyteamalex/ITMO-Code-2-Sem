package expression.generic;

import expression.base.*;
import expression.calculators.BigIntCalculator;
import expression.calculators.Calculator;
import expression.calculators.CheckedIntegerCalculator;
import expression.calculators.DoubleCalculator;
import expression.parser.GenericParser;
import expression.tools.Range;
import expression.tools.TriFunction;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GenericTabulator implements Tabulator {

    private static final Calculator<Integer> intCalculator = CheckedIntegerCalculator.INSTANCE;
    private static final Calculator<Double> doubleCalculator = DoubleCalculator.INSTANCE;
    private static final Calculator<BigInteger> bigIntCalculator = BigIntCalculator.INSTANCE;
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Calculator<?> calc = switch (mode) {
            case "i" -> intCalculator;
            case "d" -> doubleCalculator;
            case "bi" -> bigIntCalculator;
            default -> throw new UnsupportedOperationException("Mode " + mode + " not supported");
        };
        var parser = buildParser(calc);
        GenericExpression<?> expr = parser.parse(expression);
        int ix = 0;
        Object[][][] res = new Object[Math.abs(x2 - x1 + 1)][Math.abs(y2 - y1 + 1)][Math.abs(z2 - z1 + 1)];
        for (var x : Range.of(x1, x2)) {
            int iy = 0;
            for (var y : Range.of(y1, y2)) {
                int iz = 0;
                for (var z : Range.of(z1, z2)) {
                    try {
                        res[ix][iy][iz] = expr.eval(x, y, z);
                    } catch (ArithmeticException ignored) {}
                    iz++;
                }
                iy++;
            }
            ix++;
        }
        return res;
    }

    private <A, B, C, D> BiFunction<A, B, D> cut(TriFunction<A, B, C, D> func, C arg) {
        return (a, b) -> func.apply(a, b, arg);
    }

    private <A, B, C> Function<A, C> cut(BiFunction<A, B, C> func, B arg) {
        return a -> func.apply(a, arg);
    }

    private <T> GenericParser<GenericExpression<T>> buildParser(Calculator<T> calc) {
        return new GenericParser<>(Map.of(
                "+", new GenericParser.BinaryOpUnit<>(Priority.SUM, cut(GenericAdd<T>::new, calc)),
                "-", new GenericParser.BinaryOpUnit<>(Priority.SUM, cut(GenericSubtract<T>::new, calc)),
                "*", new GenericParser.BinaryOpUnit<>(Priority.MULTIPLY, cut(GenericMultiply<T>::new, calc)),
                "/", new GenericParser.BinaryOpUnit<>(Priority.DIVIDE, cut(GenericDivide<T>::new, calc))
        ), Map.of(
                "-", cut(GenericNegate<T>::new, calc)
        ), Set.of("x", "y", "z"),
                string -> new GenericConst<T>(calc.parse(string)),
                cut(GenericVariable<T>::new, calc));
    }
}
