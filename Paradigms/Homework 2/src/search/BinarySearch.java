package search;

public class BinarySearch {

    //sorted(a) = \forall 0 <= i < len-1  a[i] >= a[i+1]

    //x' -- старое значение
    //x -- новое значение

    //Пусть D(y) = (0 <= y <= len && (y == len || a[y] <= x))
    //Замечание 1 (при sorted(a)): D(y) => D(k) для y <= k <= len
    //Замечание 2 (при sorted(a)):
    //Пусть y = min { i: D(i)}
    //y == len <=> для всех 0 <= i < len a[i] > x. Тогда y == len соответствует случаю, когда искомого индекса не существует

    //Pre: a != null && sorted(a)
    //Post: D(R) && R - минимальное
    private static int searchRecursive(int x, int[] a) {
        return searchRecursive(x, a, -1, a.length);
    }

    //Pre/Inv: a != null && sorted(a) && -1 <= l < r <= len && !D(l) && D(r)
    //Post: D(R) && R - минимальное
    private static int searchRecursive(int x, int[] a, int l, int r) {
        //sorted(a) && -1 <= l < r <= len && !D(l) && D(r)
        if (r - l <= 1) {
            //sorted(a) && -1 <= l < r <= len && !D(l) && D(r) && r - l <= 1
            //Т.о. r = l + 1
            //Т.к. !D(l), то для всех y в [0, l] !D(y)
            //Тогда r - минимальное значение такое, что D(r)
            return r;
        }
        //sorted(a) && -1 <= l < r <= len && !D(l) && D(r) && r - l > 1
        int m = l + (r - l) / 2;
        //Т.к. r - l > 1, то l < m < r
        //sorted(a) && -1 <= l < m < r <= len && !D(l) && D(r)
        if (a[m] > x) {
            //sorted(a) && -1 <= l < m < r <= len && !D(l) && D(r) && a[m] > x
            l = m;
            //Т.к. a[m] > x && m != len, то !D(m)
            //sorted(a) && -1 <= l' < l < r <= len && !D(l) && D(r)
        } else {
            //sorted(a) && -1 <= l < m < r <= len && !D(l) && D(r) && a[m] <= x
            r = m;
            //Т.к. 0 <= m < len && a[m] <= x, то D(m)
            //sorted(a) && -1 <= l < r < r' <= len && !D(l) && D(r)
        }
        return searchRecursive(x, a, l, r);
    }

    // :NOTE: more formal
    //Pre: a != null && sorted(a)
    //Post: D(R) && R - минимальное
    private static int searchIterative(int x, int[] a) {
        //a != null && sorted(a)
        //Далее a != null опускается

        int l = -1;
        //sorted(a) && l == -1
        int r = a.length;
        //sorted(a) && l == -1 && r == len

        //Inv: sorted(a) && -1 <= l < r <= len && !D(l) && D(r)
        while (r - l > 1) {
            //sorted(a) && -1 <= l < r <= len && !D(l) && D(r) && r - l > 1
            int m = l + (r - l) / 2;
            //Т.к. r - l > 1, то l < m < r
            //sorted(a) && -1 <= l < m < r <= len && !D(l) && D(r)
            if (a[m] > x) {
                //sorted(a) && -1 <= l < m < r <= len && !D(l) && D(r) && a[m] > x
                l = m;
                //Т.к. a[m] > x && m != len, то !D(m)
                //sorted(a) && -1 <= l' < l < r <= len && !D(l) && D(r)
            } else {
                //sorted(a) && -1 <= l < m < r <= len && !D(l) && D(r) && a[m] <= x
                r = m;
                //Т.к. 0 <= m < len && a[m] <= x, то D(m)
                //sorted(a) && -1 <= l < r < r' <= len && !D(l) && D(r)
            }
            //Замечение: r - l < r' - l'. Отсюда цикл не бесконечный
        }
        //sorted(a) && !D(l) && D(r) && l < r && r - l <= 1
        //Т.о. r = l + 1
        //Т.к. !D(l), то для всех y в [0, l] !D(y)
        //Тогда r - минимальное значение такое, что D(r)
        return r;
    }
    
    //Pre: args != null && !ans && !err
    //Post: ans ^ err, где ans - выведен ответ, err - выведена ошибка
    public static void main(String[] args) {
        //args != null && !ans && !err
        //Далее args != null опускается
        if (args.length < 1) {
            //!ans && !err && args.length < 1
            System.err.println("Target and array values expected");
            //!ans && err
            return;
            //!ans && err
        }
        //!ans && !err && args.length >= 1
        int x;
        //!ans && !err && args.length >= 1 && !read(x)
        //Комментарий: read(x) - x успешно прочитан
        try {
            //!ans && !err && args.length >= 1 && !read(x)
            x = Integer.parseInt(args[0]);
            //!ans && !err && args.length >= 1 && read(x)
        } catch (NumberFormatException exception) {
            //!ans && !err && args.length >= 1 && !read(x)
            System.err.println("Target value is not an integer");
            //!ans && err
            return;
            //!ans && err
        }
        //!ans && !err && args.length >= 1 && read(x)
        int[] a = new int[args.length - 1];
        //!ans && !err && read(x) && !read(args) && a.length = args.length - 1
        int i = 0;
        //!ans && !err && !read(args) && i = 0 && a.length = args.length - 1
        //Inv: !ans && !err && read(x) && 0 <= i < a.length && a.length = args.length - 1 && read(a[0..i-1]) && !read(a[i..a.length]) && sorted(a[0..i-1])
        while (i < a.length) {
            //!ans && !err && read(x) && 0 <= i < a.length && a.length = args.length - 1 && read(a[0..i-1]) && !read(args[i..a.length]) && sorted(a[0..i-1])
            try {
                //!ans && !err && read(x) && 0 <= i < a.length = args.length - 1 < args.length && read(a[0..i-1]) && !read(args[i..a.length]) && sorted(a[0..i-1])
                a[i] = Integer.parseInt(args[i + 1]);
                //!ans && !err && read(x) && 0 <= i < a.length = args.length - 1 < args.length && read(a[0..i]) && !read(args[i+1..a.length]) && sorted(a[0..i-1])
                if (i != 0 && a[i - 1] < a[i]) {
                    //!ans && !err && read(x) && 0 < i < a.length = args.length - 1 < args.length && read(a[0..i]) && sorted(a[0..i-1]) && !sorted(a[i-1..i])
                    System.err.println("Array is not sorted: a[" + (i - 1) + "] < a[" + i + "]");
                    //!ans && err
                    return;
                    //!ans && err
                }
                //!ans && !err && read(x) && 0 <= i < a.length = args.length - 1 < args.length && read(a[0..i]) && !read(args[i+1..a.length]) && sorted(a[0..i])
            } catch (NumberFormatException exception) {
                //!ans && !err && read(x) && read(a[0..i-1]) && !read(args[i..a.length])
                System.err.println("Array's " + i + " value is not an integer");
                //!ans && err
                return;
                //!ans && err
            }
            //!ans && !err && read(x) && 0 <= i < a.length = args.length - 1 < args.length && read(a[0..i]) && !read(args[i+1..a.length]) && sorted(a[0..i])
            i++;
            //!ans && !err && read(x) && i = i' + 1 && 0 <= i && a.length = args.length - 1 && read(a[0..i-1]) && !read(args[i..a.length]) && sorted(a[0..i])
        }
        //!ans && !err && read(x) && read(a[0..a.length-1]) && sorted(a)
        int resIterative = searchIterative(x, a);
        //!ans && !err && read(x) && read(a[0..a.length-1]) && D(resIterative) && resIterative - минимальное && sorted(a)
        int resRecursive = searchRecursive(x, a);
        //!ans && !err && D(resIterative) && resIterative - минимальное && D(resRecursive) && resRecursive - минимальное
        assert resIterative == resRecursive;
        //!ans && !err && D(resIterative) && resIterative - минимальное && resIterative == resRecursive
        System.out.println(resIterative);
        //ans && !err
    }
}
