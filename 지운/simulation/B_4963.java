import java.io.*;
import java.util.StringTokenizer;

public class B_4963 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    static int x, y;
    static int[][] maps;
    static int[][] visited;
    static int[] dx = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = new int[] {1, 1, 0, -1, -1, -1, 0, 1};

    // 1. x, y 입력
    private static void inputXY() throws IOException {
        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
    }

    // 2. maps 채우기, 이 때 이후에 특정칸의 주변칸을 모두 확인할 것이므로 주위가 바다라는 조건을 이용해 zero padding
    private static boolean inputMapWithZeroPadding() throws IOException {
        if (x + y == 0)
            return false;

        visited = new int[x+2][y+2];
        maps = new int[x+2][y+2];
        for (int i = 1; i < x+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < y+1; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return true;
    }

    // 4. 섬 갯수 확인 단계에서, 현재 칸이 땅일 경우 주변의 연결된 모든 땅이 같은 하나의 섬으로 카운팅될 수 있도록 DFS 이용해 전부 찾아줌
    private static boolean isIsland(int i, int j) {
        if (maps[i][j] == 0 || visited[i][j] == 1)
            return false;

        visited[i][j] = 1;
        int idx = 0;
        while (idx < 8) {
            isIsland(i+dx[idx],j+dy[idx]);
            idx += 1;
        }
        return true;
    }

    // 3. 섬 갯수 확인
    private static int checkIsland() {
        int total = 0;
        for (int i = 1; i < x+1; i++) {
            for (int j = 1; j < y+1; j++) {
                if (isIsland(i, j)) {
                    total += 1;
                }
            }
        }
        return total;
    }

    // 5. 결과 출력
    private static void outputIslands(int total) {
        System.out.println(total);
    }

    public static void main(String[] args) throws IOException {
        inputXY();
        while (inputMapWithZeroPadding()) {
            outputIslands(checkIsland());
            inputXY();
        }
    }
}
