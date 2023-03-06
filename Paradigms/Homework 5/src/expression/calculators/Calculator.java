package expression.calculators;

public interface Calculator<T> extends
        Converter<Integer, T>,
        Multiplicator<T, T, T>,
        Summator<T, T, T>,
        Negator<T, T> {
    T parse(String str);
}
