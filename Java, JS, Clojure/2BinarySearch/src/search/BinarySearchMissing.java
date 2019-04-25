package search;

//import java.util.Arrays;

public class BinarySearchMissing {

    // Pre: temp[i] != null for i = 1..n
    // Post: R[i] == temp[i] for i = 1..n
    // && temp[i] == temp'[i] for i = 1..n
    public static int[] parse(String[] temp) {
        int a[] = new int[temp.length];
        // Invar: temp[i] == temp'[i]
        for (int i = 0; i < temp.length; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }
        return a;
    }


    // Pre: x != null
    // && a[i] != null for i = 0..n - 1
    // && a[i] >= a[i + 1] for i = 0..n - 2
    // Post: R >= 0
    // && a[R] = x || a[-(R + 1) - 1] > x > a[-(R + 1)]
    // && l < R
    // && a[i] > x for i = 0..R - 1
    // && a[i] <= x for i = R..n - 1
    // && x == x'
    // && a[i] == a'[i] for i = 0..n - 1
    public static int binSearch(int[] a, int x) {
        int l = -1;
        // l == - 1
        int r = a.length;
        // l == a.length && r == -1
        // Invar: l <= m <= r
        // &&  -1 <= l < r <= a.length
        // && r >= 0
        while (r - l > 1) {
            // r - l > 1
            int m = (l + r) / 2;
            // m = (l + r) / 2 && r - l > 1
            // 2m = l + r && r > l + 1
            // 2m < 2r && 2m > 2l + 1
            // l < m < r
            // m > 0
            if (x < a[m]) {
                // x < a[(l + r) / 2] && l < m < r
                l = m;
                // x < a[l] && l < r
            } else {
                // x >= a[(l + r) / 2] && && l < m < r
                r = m;
                //x >= a[r] && l < r
            }
        }
        // l < r && r - l <= 1
        // r - l = 1
        // l + 1 = r
        // r >= 0
        // if (l != -1) a[l] > x && if (r != a.length) a[r] <= x
        // && a[i] >= a[i + 1] for i = 0..n - 1
        // a[r - i] > x for i = 1..r && a[r + i] <= x for i = 0..a.length
        if (r < a.length && a[r] == x) {
            return r;
        } else {
            return - r - 1;
        }
    }


    // Pre: a[i] != null for i = 0..n - 1
    // && x != null, l != null, r != null
    // && First invocation: l == -1 && r == n
    // && if (a.length > 1) a[i] >= a[i + 1] for i = 0..n - 2
    // Post: R >= 0
    // && a[R] = x || a[-(R + 1) - 1] > x > a[-(R + 1)]
    // && l < R
    // && a[i] > x for i = 0..R - 1
    // && a[i] <= x for i = R..n - 1
    // && x == x'
    // && a[i] == a'[i] for i = 1..n
    public static int binSearchRec(int[] a, int x, int l, int r) {
        // r - l >= 1
        if (r - l <= 1) {
            // r - l <= 1
            if (r < a.length && a[r] == x) {
                return r;
            } else {
                return - r - 1;
            }
        }
        // r - l > 1
        int m = (l + r) / 2;
        // m = (l + r) / 2 && r - l > 1
        // 2m = l + r && r > l + 1
        // 2m < 2r && 2m > 2l + 1
        // l < m < r
        // m > 0
        if (x < a[m]) {
            // x < a[(l + r) / 2] && l < m < r
            return binSearchRec(a, x, m, r);
            // x < a[m] && l < r
        } else {
            // x >= a[(l + r) / 2] && l < m < r
            return binSearchRec(a, x, l, m);
            //x >= a[m] && l < r
        }
    }

    public static void main(String[] args) {
        assert (args.length != 0);
        // Pre: args[0] != null
        // Post: x == args[0]
        // && args[0] == args'[0]
        int x = Integer.parseInt(args[0]);
        String[] temp = new String[args.length - 1];
        // Pre: args[i] != null for i = 1..n
        // Post: temp[i] == args[i + 1] for i = 1..n - 1
        // && args[i] == args'[i] for i = 1..n
        System.arraycopy(args, 1, temp, 0, args.length - 1);
        int[] a = parse(temp);
        //System.out.println(binSearch(a, x));
        System.out.println(binSearchRec(a, x, -1, a.length));
    }
}

//∀Ǝ