package 예지.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quit {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[] T, P, dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        T = new int[N];
        P = new int[N];
        dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            T[i] = Integer.parseInt(tokens.nextToken());
            P[i] = Integer.parseInt(tokens.nextToken());
        }

        for (int i = 0; i < N ; i++) {
            if(i + T[i] <= N ){
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }
        System.out.println(dp[N]);
    }
}
