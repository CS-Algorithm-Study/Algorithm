import java.io.*;
import java.util.StringTokenizer;

public class B_2573 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    private static int N;
    private static int M;
    private static int[][] maps;
    private static int[][] newMaps;
    private static int[][] visited;
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};

    // 1. N, M 및 maps, newMaps(1년 뒤의 maps 로 활용) 정보 입력
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new int[N][M];
        newMaps = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                newMaps[i][j] = maps[i][j];
            }
        }
    }

    private static int checkAdj(int i, int j) {
        if (maps[i][j] == 0) return 1;
        return 0;
    }

    private static void addAdjVisited(int i, int j) {
        if (maps[i][j] == 0 || visited[i][j] == 1)
            return;
        visited[i][j] = 1;
        for (int idx = 0; idx < dx.length; idx++)
            addAdjVisited(i + dx[idx], j + dy[idx]);
    }

    private static void calculateNext(int i, int j, int zeroes) {
        if (maps[i][j] > zeroes) {
            newMaps[i][j] -= zeroes;
            return;
        }
        newMaps[i][j] = 0;
    }

    // 2. 1년마다 다음 해의 newMaps 를 규칙에 따라 생성하면서 현재 섬이 두 개 이상으로 분리되어있는지 확인
    // maps 에 바로 값을 바꿔버리면 그 이후의 칸을 작업할 때 이 값에 영향을 받는 칸이 존재하기 때문에, 다음 해의 결과는 newMaps 에 따로 저장
    private static int resumeYear() {
        int island = 0;
        boolean flag = false;
        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < M-1; j++) {
                // 섬이 아닌 경우 할 일이 없으므로 넘어감
                if (maps[i][j] == 0)
                    continue;
                // 처음 방문한 빙산일 경우 섬 +1
                if (visited[i][j] == 0)
                    island += 1;
                // 상하좌우 체크하여 주변에 바다가 몇 개 있는지 합산
                int zeroes = 0;
                for (int idx = 0; idx < dx.length; idx++) {
                    int isIsland = checkAdj(i + dx[idx], j + dy[idx]);
                    // 이 때 인접한 곳이 빙산일 경우 현재 방문한 빙산과 이어져있어 하나의 섬으로 봐야 하므로, dfs 를 활용해 방문 처리
                    if (isIsland == 0)
                        addAdjVisited(i + dx[idx], j + dy[idx]);
                    zeroes += isIsland;
                }
                // 규칙에 따라 현재 칸의 다음 해의 빙산 depth 를 계산하여 newMaps 에 저장
                calculateNext(i, j, zeroes);
                // 예외조건. 현재 지도에 빙산이 아예 없는 경우를 찾아내는 case
                if (!flag && maps[i][j] > 0)
                    flag = true;
            }
        }
        if (!flag)
            return -1;
        if (island > 1)
            return 1;
        return 0;
    }

    // 3. 출력
    private static void outputTotal(int total) throws IOException {
        bw.write(String.valueOf(total));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        int year = 0;
        while (true) {
            visited = new int[N][M];
            int result = resumeYear();
            // 다음 해에 대한 newMaps 를 바로 위 함수에서 구했으므로 이제 maps 를 newMaps 에 깊은 복사
            for (int i = 0; i < N; i++)
                System.arraycopy(newMaps[i], 0, maps[i], 0, M);
            if (result == -1) {
                year = 0;
                break;
            }
            if (result == 1)
                break;
            year++;
        }
        outputTotal(year);
    }
}
