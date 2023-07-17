package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N, M, year, total;
    static boolean[][] visited;
    enum Type {
        MELT, CHECK;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        year = 0;
        for (int i = 0;i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int height = Integer.parseInt(st.nextToken());
                map[i][j] = height;
                total += height;
            }
        }

        while (true) {
            if (isSeparated()) {
                System.out.println(year);
                break;
            } else if (total == 0) {
                System.out.println(total);
                break;
            }
            melt();
            year += 1;
        }
    }

    public static void melt() {
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    conquer(j, i, Type.MELT);
                }
            }
        }
    }

    public static boolean isSeparated() {
        visited = new boolean[N][M];
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    conquer(j, i, Type.CHECK);
                    count += 1;
                }
            }
        }
        if (count >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static void conquer(int x, int y, Type type) {
        int nx = 0;
        int ny = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                nx = node.x + dx[i];
                ny = node.y + dy[i];
                if (0 <= nx && nx < M && 0 <= ny && ny < N && !visited[ny][nx]) {
                    if (type == Type.MELT && map[ny][nx] == 0 && map[node.y][node.x] > 0) {
                        map[node.y][node.x] -= 1;
                        total -= 1;
                    }
                    if (map[ny][nx] != 0) {
                        visited[ny][nx] = true;
                        q.add(new Node(nx, ny));
                    }
                }
            }
        }
    }
}
