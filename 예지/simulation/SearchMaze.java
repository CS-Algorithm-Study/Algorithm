package 예지.simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * [문제]
 * - 1: 이동할 수 있는 칸, 0 : 이동할 수 없는 칸
 * (1,1)에서 출발하여 (N,M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하기
 * - 첫째 줄에 N,M이 주어짐, (N,M)의 행렬
 *
 * [풀이]
 * - 처음 위치부터 인접한 칸을 큐에 넣고 하나씩 조회하면서 그 전 거리 +1
 */

class Point {
    public int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class SearchMaze {

    static int[] dx = {0, 1, 0, -1}; // 동, 남, 서, 북
    static int[] dy = {1, 0, -1, 0};
    static int[][] maze, dist;
    static int N, M;

    public boolean in_range(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }


    public int BFS(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        maze[x][y] = 0;
        while (!q.isEmpty()) {
            Point tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (in_range(nx, ny) && maze[nx][ny] == 1) {
                    maze[nx][ny] = 0;
                    q.offer(new Point(nx, ny));
                    dist[nx][ny] = dist[tmp.x][tmp.y] + 1;
                }
            }
        }
        return dist[N-1][M-1];
    }

    public static void main(String[] args) {
        SearchMaze S = new SearchMaze();
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        M = input.nextInt();

        maze = new int[N][M];
        dist = new int[N][M];
        dist[0][0] = 1;

        for (int i = 0; i < N; i++) {
            String str = input.next();
            for (int j = 0; j < str.length(); j++) {
                maze[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        System.out.println(S.BFS(0,0));
    }
}
