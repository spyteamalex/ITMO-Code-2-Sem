package expression.tools;

import expression.exceptions.exceptions.OverflowException;
import expression.exceptions.exceptions.UnsupportedOperandsException;

public class MathTools {
    public static int exactGCD(int a, int b) {
        while (b != 0 && a != 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return Math.abs(a) + Math.abs(b);
    }

    public static int exactLCM(int a, int b) {
        if (a == 0 && b == 0) {
            return 0;
        }
        try {
            return exactMultiply(a / MathTools.exactGCD(a, b), b);
        } catch (OverflowException exception) {
            throw new OverflowException("Overflow while taking lcm of " + a + " by " + b);
        }
    }

    /**
     * Compares digit with the bounds
     * @return -1 - if digit is out of bounds, 0 - if digit is on a bound or 1 - if digit is in bounds
     */
    private static int compareDigit(int digit, int minBound, int maxBound) {
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
        int compareRes = -1000000000 < value && value < 1000000000 ? 1 : 0;
        for (int i = 0; value != 0; i++) {
            result *= 10;
            int digit = value % 10;
            result += digit;
            value /= 10;
            if (compareRes == 0) {
                compareRes = compareDigit(digit, minBound[i], maxBound[i]);
            }
        }
        ErrorTools.require(compareRes >= 0, new OverflowException("Overflow while reversing " + origin));
        return result;
    }

    public static int min(int a, int b) {
        return a > b ? b : a;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static int exactSum(int a, int b) {
        boolean aSign = a >= 0;
        boolean bSign = b >= 0;
        int result = a + b;
        boolean resultSign = result >= 0;
        ErrorTools.require((aSign == resultSign) || (bSign == resultSign),
                new OverflowException("Overflow while summing " + a + " and " + b));
        return result;
    }

    public static int exactMultiply(int a, int b) {
        ArithmeticException exception = new OverflowException("Overflow while multiplying " + a + " and " + b);
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a > 0) {
            if (b > 0) {
                ErrorTools.require(a <= Integer.MAX_VALUE / b, exception);
            } else {
                ErrorTools.require(b >= Integer.MIN_VALUE / a, exception);
            }
        } else {
            if (b > 0) {
                ErrorTools.require(a >= Integer.MIN_VALUE / b, exception);
            } else {
                ErrorTools.require(a >= Integer.MAX_VALUE / b, exception);
            }
        }
        return a * b;
    }

    public static int exactDivide(int a, int b) {
        ErrorTools.require(b != 0, new UnsupportedOperandsException("Division by zero"));
        ErrorTools.require(a != Integer.MIN_VALUE || b != -1,
                new OverflowException("Overflow while dividing " + a + " by " + b));
        return a / b;
    }

    public static int exactSubtract(int a, int b) {
        int result = a - b;
        boolean aSign = a >= 0;
        boolean bSign = b >= 0;
        boolean resultSign = result >= 0;
        ErrorTools.require((aSign == resultSign) || (bSign != resultSign),
                new OverflowException("Overflow while subtracting " + a + " and " + b));
        return result;
    }

    public static int exactNegate(int a) {
        ErrorTools.require(a != Integer.MIN_VALUE, new OverflowException("Overflow while taking negate from " + a));
        return -a;
    }
    public static int exactLog10(int a) {
        ErrorTools.require(a > 0, new UnsupportedOperandsException("Can't take log of non-positive number"));
        int res = 0;
        while (a >= 10) {
            a /= 10;
            res += 1;
        }
        return res;
    }

    public static int exactPow10(int a) {
        ErrorTools.require(a <= 9, new OverflowException("Overflow while taking pow 10 from " + a));
        ErrorTools.require(a >= 0, new UnsupportedOperandsException("Can't take pow 10 of negative number"));
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
