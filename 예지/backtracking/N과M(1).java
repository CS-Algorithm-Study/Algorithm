package 예지.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class NM {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
    static int N, M;
    static int[] numbers;
    static boolean[] visited;


    public void dfs(int cnt) {
        if (cnt == M) {
            for (int i = 0; i < numbers.length; i++) {
                output.append(numbers[i]).append(" ");
            }
            output.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                numbers[cnt] = i + 1;
                dfs(cnt + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NM NM = new NM();
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        numbers = new int[M];
        visited = new boolean[N];
        NM.dfs(0);
        System.out.println(output);
    }

}
