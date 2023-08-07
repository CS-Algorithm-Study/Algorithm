package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638 {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int N, M;
    public static int[][] board;
    public static int[][] copyBoard;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        copyBoard = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        copy();
        go();
    }

    public static void go() {
        int time = 0;
        while (!isEnd()) {
            checkOutside();
            melt();
            copy();
            time += 1;
        }
        System.out.println(time);
    }

    public static void checkOutside() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        boolean[][] visited = new boolean[N][M];

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;
                if (0 <= nx && nx < M && 0 <= ny && ny < N && !visited[ny][nx] && board[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    copyBoard[ny][nx] = -1;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    public static void melt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyBoard[i][j] == 1 && isMelt(j, i)) {
                    board[i][j] = 0;
                }
            }
        }
    }

    public static boolean isMelt(int x, int y) {
        int count = 0;
        for (int i = 0 ; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (0 <= nx && nx < M && 0 <= ny && ny < N && copyBoard[ny][nx] == -1) {
                count += 1;
            }
        }
        if (count >= 2) return true;
        return false;
    }

    public static boolean isEnd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) return false;
            }
        }
        return true;
    }

    public static void copy() {
        for (int i = 0; i < N; i++) {
            copyBoard[i] = board[i].clone();
        }
    }

}
