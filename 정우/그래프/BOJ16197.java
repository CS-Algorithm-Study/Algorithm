package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16197 {

    static class Point {
        int x, y, count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static int N, M;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static String[][] board;
    public static List<Point> startCoinPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new String[N][M];
        startCoinPoint = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String token = st.nextToken();
            for (int j = 0; j < M; j++) {
                String temp = String.valueOf(token.charAt(j));
                if (temp.equals("o"))  {
                    startCoinPoint.add(new Point(j, i, 0));
                    board[i][j] = ".";
                    continue;
                }
                board[i][j] = temp;
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(startCoinPoint.get(0));
        q.add(startCoinPoint.get(1));

        while (!q.isEmpty()) {
            Point coin1 = q.poll();
            Point coin2 = q.poll();
            if (coin1.count >= 10) return -1;

            for (int i = 0; i < 4; i++) {
                int nx1 = dx[i] + coin1.x;
                int ny1 = dy[i] + coin1.y;
                int nx2 = dx[i] + coin2.x;
                int ny2 = dy[i] + coin2.y;
                if (isOut(nx1, ny1) && isOut(nx2, ny2)) continue;
                if (isEnd(nx1, nx2, ny1, ny2)) {
                    return coin1.count + 1;
                }
                q.add(move(coin1, nx1, ny1));
                q.add(move(coin2, nx2, ny2));
            }
        }
        return -1;
    }

    public static Point move(Point coin, int nx, int ny) {
        if (board[ny][nx].equals(".")) {
            return new Point(nx, ny, coin.count + 1);
        }
        return new Point(coin.x, coin.y, coin.count + 1);
    }

    public static boolean isEnd(int nx1, int nx2, int ny1, int ny2) {
        if (isOut(nx1, ny1) || isOut(nx2, ny2)) return true;
        return false;
    }

    public static boolean isOut(int x, int y) {
        if (0 > x || x >= M || 0 > y || y >= N) return true;
        return false;
    }

}
