package expression.tools;

import java.util.Arrays;

public class CharStack {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MIN_CAPACITY = 2;
    private char[] data;
    private int size = 0;

    public CharStack() {
        data = new char[DEFAULT_CAPACITY];
    }

    public CharStack(int capacity) {
        data = new char[capacity];
    }

    public void resize(int newSize) {
        data = Arrays.copyOf(data, MathTools.max(newSize, MIN_CAPACITY));
    }

    public void add(char chr) {
        if (size == data.length) {
            resize(2 * size);
        }
        data[size++] = chr;
    }

    public void add(String str) {
        if (size + str.length() > data.length) {
            resize(MathTools.max(size + str.length(), data.length * 2));
        }
        str.getChars(0, str.length(), data, size);
        size += str.length();
    }

    public char pop() {
        char res = data[--size];
        if (4 * size <= data.length) {
            resize(2 * size);
        }
        return res;
    }

    public String pop(int count) {
        char[] res = new char[MathTools.min(count, size)];
        for (int i = 0; i < count && size > 0; i++) {
            res[i] = data[--size];
        }
        return String.valueOf(res);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public char top() {
        return data[size - 1];
    }
}
