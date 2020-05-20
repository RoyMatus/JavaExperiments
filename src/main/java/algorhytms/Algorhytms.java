package algorhytms;

import java.util.logging.Logger;

public class Algorhytms {

    static long[] c = new long[3000];
    Logger logger = Logger.getLogger(this.getClass().getName());

    public String stringRevert(String original) {
        String result = "";
        char[] array = original.toCharArray();
        for (int i = 0; i < array.length; i++) {
            result = result + array[array.length - i - 1];
        }
        return result;
    }

    public String stringRevert2(String original) {
        String rightPart = "";
        String leftPart = "";

        if (original.length() == 1) return original;

        leftPart = original.subSequence(0, original.length() / 2).toString();
        rightPart = original.subSequence(original.length() / 2, original.length()).toString();

        return stringRevert2(rightPart) + stringRevert2(leftPart);
    }

    public long fibo(int n) {
        final int MAXN = 1000;
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (c[n] > 0) return c[n];
        return fibo(n - 1) + fibo(n - 2);
    }

}
