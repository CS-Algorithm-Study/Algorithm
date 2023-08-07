package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2667 {
    public static int N, complexCount;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static String[][] board;
    public static boolean[][] visited;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> houseCounts = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());
        board = new String[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            for (int j = 0; j < N; j++) {
                board[i][j] = String.valueOf(input.charAt(j));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals("1") && !visited[i][j]) {
                    houseCounts.add(bfs(j, i));
                    complexCount += 1;
                }
            }
        }

        Collections.sort(houseCounts);

        System.out.println(complexCount);
        for (Integer houseCount : houseCounts) {
            System.out.println(houseCount);
        }
    }

    public static int bfs(int x, int y) {
        int houseCount = 1;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Point pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[ny][nx] && board[ny][nx].equals("1")) {
                    visited[ny][nx] = true;
                    q.add(new Point(nx, ny));
                    houseCount += 1;
                }
            }
        }

        return houseCount;
    }
}
