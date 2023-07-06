import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_15649 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, M;
    static boolean[] visited;
    static int[] per;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        per = new int[M];
    }

    private static void permutation(int[] per, int depth, int N, int M) throws IOException {
        if (depth == M) {
            for (int i = 0; i < M; i++)
                bw.write(per[i] + " ");
            bw.write("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                per[depth] = i+1;
                permutation(per, depth+1, N, M);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        permutation(per, 0, N, M);
        bw.flush();
    }
}
