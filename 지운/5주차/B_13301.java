import java.io.*;
import java.util.StringTokenizer;

public class B_13301 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    static int N;
    static long[] dp;

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1];
    }

    private static long getTotal(int n) {
        if (n == 1)
            return dp[n] = 4L;
        if (n == 2)
            return dp[n] = 6L;
        if (dp[n] != 0)
            return dp[n];
        return dp[n] = getTotal(n-1) + getTotal(n-2);
    }

    private static void outputTotal(long total) throws IOException {
        bw.write(String.valueOf(total));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        outputTotal(getTotal(N));
    }
}
