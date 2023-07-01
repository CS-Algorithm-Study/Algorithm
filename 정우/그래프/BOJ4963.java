package 그래프;

/*
    풀이 방법 -> 상하좌우와 대각선을 고려한 BFS를 이용
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4963 {
    static int W, H, nx, ny, result;
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[][] board;
    static boolean[][] visited;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) {
                break;
            }
            board = new int[H][W];
            visited = new boolean[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (!visited[i][j] && board[i][j] == 1) {
                        bfs(j, i);
                        result += 1;
                    }
                }
            }

            System.out.println(result);
            result = 0;
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[y][x] = true;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int i = 0; i < 8; i++) {
                nx = pos[0] + dx[i];
                ny = pos[1] + dy[i];
                if (0 <= nx && nx < W && 0 <= ny && ny < H && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    if (board[ny][nx] == 1) {
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
