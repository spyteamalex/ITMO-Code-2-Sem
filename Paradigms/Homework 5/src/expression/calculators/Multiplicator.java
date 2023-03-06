package expression.calculators;

public interface Multiplicator<Left, Right, Res> {
    Res multiply(Left left, Right right);

    Res divide(Left left, Right right);

}
