package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();
    char next();
    boolean hasPrev();
    char prev();
}
