import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class B_15900 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    private static ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
    private static boolean[] visited;
    private static int total = 0;

    // 1. 입력값, 초기 설정, 트리 관계를 인접리스트로 구현(중첩)
    private static void input() throws IOException {
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        for (int i = 0; i < N+1; i++)
            nodes.add(new ArrayList<>());
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes.get(x).add(y);
            nodes.get(y).add(x);
        }
    }

    // 2. dfs 를 이용해 모든 노드 방문
    private static void dfs(int node, int depth) {
        visited[node] = true;

        for (int childNode : nodes.get(node)) {
            if (!visited[childNode])
                dfs(childNode, depth+1);
        }

        // 이 때, 현재 노드의 하위 노드 리스트 길이가 1(=연결된 부모노드 1개밖에 없는, 리프 노드)이면서 루트 노드가 아닐 경우 depth 누적
        if (nodes.get(node).size() == 1 && node != 1)
            total += depth;
    }

    // 3. 홀수일 경우 승리가능, 짝수일 경우 승리불가능 출력
    private static void outputTotal() throws IOException {
        if (total % 2 == 1)
            bw.write("Yes");
        else
            bw.write("No");
        bw.write(String.valueOf(total));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        dfs(1, 0);
        outputTotal();
    }
}
