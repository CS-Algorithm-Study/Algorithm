import java.util.*;
import java.io.*;

public class B_14501 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] T;
    static int[] P;
    static int[] dp;

    private static void inputConfig() throws IOException {
        N = Integer.parseInt(br.readLine());
        T = new int[N+1];
        P = new int[N+1];
        dp = new int[N+2];

        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int getMaxPrice() {
        for(int i = N; i > 0; i--) {
            int next = i + T[i]; // 다음 날짜

            if(next > N+1) { // 일할 수 있는 날짜를 넘어가는 경우
                // 일을 하지 못하므로 바로 다음 DP값(더 앞쪽의 날짜)로 설정
                dp[i] = dp[i+1];
            } else { // 일할 수 있는 날짜인 경우
                // 1. 일하지 않았을 때(DP[i+1])
                // 2. 일 했을 때 총 벌 수 있는 금액(P[i] + DP[next])
                // 위 두 경우 중 더 큰 값을 DP에 넣어준다.
                dp[i] = Math.max(dp[i+1], P[i] + dp[next]);
            }
        }
        return dp[1];
    }

    private static void outputTotal(int total) throws IOException {
        bw.write(String.valueOf(total));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        inputConfig();
        outputTotal(getMaxPrice());
    }
}
