package 백트레킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {
    public static int[] nums;
    public static boolean[] visited;
    public static int N, M, depth;
    public static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[M];
        visited = new boolean[N + 1];
        sb = new StringBuilder();
        depth = 0;
        go(0);
        System.out.println(sb);
    }

    public static void go(int n) {
        if (depth == M) {
            for (int c : nums) {
                sb.append(c + " ");
            }
            // 혹시 몰라서 뒤에 공백 삭제 -> 삭제 안해도 맞긴함
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                depth += 1;
                nums[n] = i + 1;
                visited[i] = true;
                go(n + 1);
                visited[i] = false;
                depth -= 1;
            }
        }
    }
}
