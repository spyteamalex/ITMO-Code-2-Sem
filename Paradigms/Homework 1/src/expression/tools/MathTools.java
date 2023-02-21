package expression.tools;

import expression.exceptions.exceptions.OverflowException;
import expression.exceptions.exceptions.UnsupportedOperandsException;

public class MathTools {
    public static int exactGCD(int a, int b) {
        while (b != 0 && a != 0) {
            final int c = a % b;
            a = b;
            b = c;
        }
        return Math.abs(a) + Math.abs(b);
    }

    public static int exactLCM(final int a, final int b) {
        if (a == 0 && b == 0) {
            return 0;
        }
        try {
            return exactMultiply(a / MathTools.exactGCD(a, b), b);
        } catch (final OverflowException exception) {
            throw new OverflowException("Overflow while taking lcm of " + a + " by " + b);
        }
    }

    /**
     * Compares digit with the bounds
     * @return -1 - if digit is out of bounds, 0 - if digit is on a bound or 1 - if digit is in bounds
     */
    private static int compareDigit(final int digit, final int minBound, final int maxBound) {
        if (minBound < digit && digit < maxBound) {
            return 1;
        } else if (minBound == digit || digit == maxBound) {
            return 0;
        } else {
            return -1;
        }
    }

    private final static int[] maxBound = new int[] { 2, 1, 4, 7, 4, 8, 3, 6, 4, 7 };
    private final static int[] minBound = new int[] { -2, -1, -4, -7, -4, -8, -3, -6, -4, -8 };
    public static int exactReverse(int value) {
        int result = 0;
        final int origin = value;
        int compareRes = -1000000000 < value && value < 1000000000 ? 1 : 0;     //-1 - out of bounds, 0 - on a bound, 1 - in bounds
        for (int i = 0; value != 0; i++) {
            result *= 10;
            final int digit = value % 10;
            result += digit;
            value /= 10;
            if (compareRes == 0) {
                compareRes = compareDigit(digit, minBound[i], maxBound[i]);
            }
        }
        if(compareRes < 0) {
            throw new OverflowException("Overflow while reversing " + origin);
        }
        return result;
    }

    public static int min(final int a, final int b) {
        return a > b ? b : a;
    }

    public static int max(final int a, final int b) {
        return a > b ? a : b;
    }

    public static int exactSum(final int a, final int b) {
        final boolean aSign = a >= 0;
        final boolean bSign = b >= 0;
        final int result = a + b;
        final boolean resultSign = result >= 0;
        if ((aSign != resultSign) && (bSign != resultSign)) {
            throw new OverflowException("Overflow while summing " + a + " and " + b);
        }
        return result;
    }

    private static RuntimeException multiplyException(final int a, final int b) {
        return new OverflowException("Overflow while multiplying " + a + " and " + b);
    }
    public static int exactMultiply(final int a, final int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a > 0) {
            if (b > 0) {
                if (a > Integer.MAX_VALUE / b) {
                    throw multiplyException(a, b);
                }
            } else {
                if (b < Integer.MIN_VALUE / a) {
                    throw multiplyException(a, b);
                }
            }
        } else {
            if (b > 0) {
                if (a < Integer.MIN_VALUE / b) {
                    throw multiplyException(a, b);
                }
            } else {
                if (a < Integer.MAX_VALUE / b) {
                    throw multiplyException(a, b);
                }
            }
        }
        return a * b;
    }

    public static int exactDivide(final int a, final int b) {
        if (b == 0) {
            throw new UnsupportedOperandsException("Division by zero");
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            // :NOTE: Message arsing (добавить поля)
            throw new OverflowException("Overflow while dividing " + a + " by " + b);
        }
        return a / b;
    }

    public static int exactSubtract(final int a, final int b) {
        final int result = a - b;
        final boolean aSign = a >= 0;
        final boolean bSign = b >= 0;
        final boolean resultSign = result >= 0;
        if ((aSign != resultSign) && (bSign == resultSign)) {
            throw new OverflowException("Overflow while subtracting " + a + " and " + b);
        }
        return result;
    }

    public static int exactNegate(final int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow while taking negate from " + a);
        }
        return -a;
    }
    public static int exactLog10(int a) {
        if (a <= 0) {
            throw new UnsupportedOperandsException("Can't take log of non-positive number");
        }
        int res = 0;
        while (a >= 10) {
            a /= 10;
            res += 1;
        }
        return res;
    }

    public static int exactPow10(final int a) {
        if (a > 9){
            throw new OverflowException("Overflow while taking pow 10 from " + a);
        }
        if (a < 0) {
            throw new UnsupportedOperandsException("Can't take pow 10 of negative number");
        }
        int res = 1;
        if ((a & 0b1) != 0) {
            res *= 10;
        }
        if ((a & 0b10) != 0) {
            res *= 100;
        }
        if ((a & 0b100) != 0) {
            res *= 10000;
        }
        if ((a & 0b1000) != 0) {
            res *= 100000000;
        }
        return res;
    }
}
