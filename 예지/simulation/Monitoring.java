package 예지.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Monitoring {
    static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌 (시계 방향)
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] office;
    static List<Point> cctv = new ArrayList<>();
    static int blindSpotSize = 100;
    static int nx, ny;

    static class Point {
        int x, y, type;

        Point(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public boolean in_range(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void monitor(int t, int x, int y) {
        int tt = t % 4;
        switch (tt) {
            case 0:
            case 2: // 위, 아래
                for (int i = 0; i < N; i++) {
                    nx = x + dx[tt];
                    if (in_range(nx, y)) {
                        if (office[nx][y] == 6) break;
                        if (office[nx][y] != 0 ) {
                            x = nx;
                            continue;
                        }
                        office[nx][y] = -1;
                        x = nx;
                    }
                }
                break;

            case 1:
            case 3: // 오른쪽, 왼쪽
                for (int i = 0; i < M; i++) {
                    ny = y + dy[tt];
                    if (in_range(x, ny)) {
                        if (office[x][ny] == 6) break;
                        if (office[x][ny] != 0){
                            y = ny;
                            continue;
                        }
                        office[x][ny] = -1;
                        y = ny;
                    }
                }
                break;
        }
    }

    public void solution(int idx) {
        if (idx == cctv.size()) {
            int blindSpot = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0 ; j < M; j++) {
                    if (office[i][j] == 0) blindSpot++;
                }
            }
            blindSpotSize = Math.min(blindSpotSize, blindSpot);
            return;
        }

        Point p = cctv.get(idx);
        int x = p.x;
        int y = p.y;
        int t = p.type;

        for (int i = 0; i < 4; i++) {
            int[][] copy = new int[N][M];
            for (int j = 0; j < N; j++) {
                System.arraycopy(office[j], 0, copy[j], 0, M);
            }

            // 모니터 종류에 따라서 감시하기
            switch (office[x][y]) {
                case 1:
                    monitor(i, x, y);
                    break;
                case 2:
                    monitor(i, x, y);
                    monitor(i + 2, x, y);
                    break;
                case 3:
                    monitor(i, x, y);
                    monitor(i+1, x, y);
                    break;
                case 4:
                    monitor(i, x, y);
                    monitor(i+1, x, y);
                    monitor(i + 3, x, y);
                    break;
                case 5:
                    monitor(i, x, y);
                    monitor(i+1, x, y);
                    monitor(i+2, x, y);
                    monitor(i + 3, x, y);
                    break;
            }

            solution(idx + 1);

            for (int j = 0; j < N; j++) {
                System.arraycopy(copy[j], 0, office[j], 0, M);
            }
        }
    }


    public static void main(String[] args) {
        Monitoring MO = new Monitoring();
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        M = input.nextInt();

        office = new int[N][M];

        // 사무실 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                office[i][j] = input.nextInt();
                // cctv 위치 저장
                if (office[i][j] != 6 && office[i][j] != 0) {
                    cctv.add(new Point(i, j , office[i][j]));
                }
            }
        }
        MO.solution(0);
        System.out.println(blindSpotSize);
    }
}
