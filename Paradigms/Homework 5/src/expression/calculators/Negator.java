package expression.calculators;

public interface Negator<Op, Res> {
    Res negate(Op op);
}
