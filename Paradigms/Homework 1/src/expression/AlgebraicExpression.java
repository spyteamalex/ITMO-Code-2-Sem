package expression;

public interface AlgebraicExpression extends Expression, TripleExpression, ToMiniString {
    Priority getPriority();
    default int evaluate(int var) {
        return evaluate(var, var, var);
    }
}
