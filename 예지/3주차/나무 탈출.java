package 예지.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class TreeEscape {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer>[] node = new ArrayList[500001];
    static boolean[] visited;
    static int N;
    static int total = 0;


    public static void dfs(int v, int height) {

        visited[v] = true;
        boolean is_leaf = true;

        for (int next : node[v]) {
            if (!visited[next]) {
                is_leaf = false;
                dfs(next, height + 1);
            }
        }

        if (is_leaf) {
            total += height;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer token = new StringTokenizer(input.readLine());

            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());

            node[x].add(y);
            node[y].add(x);
        }

        dfs(1, 0);

        if (total % 2 == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}
