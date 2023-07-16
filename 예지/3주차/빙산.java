package 예지.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Iceberg {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌 (시계 방향)
    static int[] dy = {0, 1, 0, -1};
    static int year = 0;

    public static void dfs(int x, int y, int[][] m){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                if(m[nx][ny] != 0){
                    m[nx][ny] = 0;
                    dfs(nx, ny, m);
                }
            }
        }
    }

    public static int getIceberg(int[][] map) {
        // 여기서 넘긴 배열 깊은 복사 한걸로 바꿔야 됨... 안그럼 원래 배열이 바뀜!
        int[][] m = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, m[i], 0, M);
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] != 0) {
                    cnt++;
                    m[i][j] = 0;
                    dfs(i, j, m);
                }
            }
        }
        return cnt;
    }

    public static void melt(int[][] map) {
        while (true) {
            // 만약에 2개 이상이거나 끝까지 다 녹았으면 끝
            if (getIceberg(map) >= 2) {
                System.out.println(year);
                break;
            }
            if (getIceberg(map) == 0){
                System.out.println(0);
                break;
            }

            // 녹은 빙산을 저장할 배열
            int[][] temp = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0) {
                        int cnt = 0;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                                if (map[nx][ny] == 0) {
                                    cnt++;
                                }
                            }
                        }
                        // 녹은게 0보다 작으면 0으로
                        if (map[i][j] - cnt < 0) {
                            temp[i][j] = 0;
                        } else {
                            temp[i][j] = map[i][j] - cnt;
                        }
                    }
                }
            }
            // 녹은 빙산을 원래 배열에 복사
            for(int i = 0; i < N; i++){
                System.arraycopy(temp[i], 0, map[i], 0, M);
            }
            year++;
        }

    }

    public static void main (String[]args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        melt(map);
    }
}

