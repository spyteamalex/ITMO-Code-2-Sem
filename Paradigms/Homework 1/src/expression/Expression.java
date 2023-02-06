package expression;

import base.Asserts;
import base.ExtendedRandom;
import base.Pair;
import base.TestCounter;
import expression.common.ExpressionKind;
import expression.common.Type;

import java.util.List;

/**
 * One-argument arithmetic expression over integers.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
@FunctionalInterface
@SuppressWarnings("ClassReferencesSubclass")
public interface Expression extends ToMiniString {

    int evaluate(int x);
}
