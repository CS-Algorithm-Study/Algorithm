package 그래프;

/*
    풀이 방법 -> BFS의 정석적인 풀이를 이용(Queue 사용)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] board;
    static boolean[][] visited;
    static int N, M, ny, nx, result;

    // 좌표의 값과 count 값을 함께 저장하기 위한 Node 클래스 정의
    static class Node {
        int x, y, count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }
        bfs();
        System.out.println(result);
    }

    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.x == M - 1 && node.y == N - 1) {
                result = node.count;
                break;
            }
            for (int i = 0; i < 4; i++) {
                nx = node.x + dx[i];
                ny = node.y + dy[i];
                if (0 <= nx && nx < M && 0 <= ny && ny < N && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    if (board[ny][nx] == 1) {
                        q.add(new Node(nx, ny, node.count + 1));
                    }
                }
            }
        }
    }
}
