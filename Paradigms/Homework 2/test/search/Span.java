package search;

public class Span {
    private static int searchIterative(int x, int[] a, boolean inc) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (a[m] < x || inc & a[m] == x) {
                l = m;
            } else {
                r = m;
            }
        }
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
                if (i != 0 && a[i - 1] > a[i]) {
                    //!ans && !err && read(x) && 0 < i < a.length = args.length - 1 < args.length && read(a[0..i]) && sorted(a[0..i-1]) && !sorted(a[i-1..i])
                    System.err.println("Array is not sorted: a[" + (i - 1) + "] > a[" + i + "]");
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
        int resIterative = searchIterative(x, a, true) - searchIterative(x, a, false);
        //!ans && !err && read(x) && read(a[0..a.length-1]) && D(resIterative) && resIterative - минимальное && sorted(a)
//todo        int resRecursive = searchRecursive(x, a);
        //!ans && !err && D(resIterative) && resIterative - минимальное && D(resRecursive) && resRecursive - минимальное
//todo        assert resIterative == resRecursive;
        //!ans && !err && D(resIterative) && resIterative - минимальное && resIterative == resRecursive
        System.out.println(resIterative);
        //ans && !err
    }
}
