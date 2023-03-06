package expression.calculators;

public interface Summator<Left, Right, Res> {
    Res add(Left left, Right right);

    Res subtract(Left left, Right right);
}
