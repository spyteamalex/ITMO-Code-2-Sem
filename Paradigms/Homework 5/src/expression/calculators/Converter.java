package expression.calculators;

public interface Converter<Op, Res> {
    Res to(Op op);
}
