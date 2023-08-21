import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tile {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        long[] dp = new long[81];
        dp[0] = 4L;
        dp[1] = 6L;
        for(int i = 2; i < N; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[N-1]);
    }
}
