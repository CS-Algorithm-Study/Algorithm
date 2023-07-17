package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15900 {
    static boolean[] visited;
    static List<Integer>[] nodes;
    static int count, N;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList[N+1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes[a].add(b);
            nodes[b].add(a);
        }
        dfs(1, 0);
        if (count % 2 == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    public static void dfs(int node, int cnt) {
        if (node != 1 && nodes[node].size() == 1) {
            count += cnt;
            return;
        }
        for (int idx : nodes[node]) {
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(idx, cnt + 1);
                visited[idx] = false;
            }
        }
    }
}
