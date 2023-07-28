import java.io.*;
import java.util.StringTokenizer;

public class B_2638 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    static int x, y;
    static int[][] cheese;
    static int[][] air;
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};

    // 1. 입력값 저장
    private static void inputConfig() throws IOException {
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        air = new int[x][y];
        cheese = new int[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++)
                cheese[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    // 3. 공기 칸 찾기 dfs
    private static void dfs(int i, int j) {
        if (i < 0 || i >= cheese.length || j < 0 || j >= cheese[0].length || air[i][j] == 1 || cheese[i][j] == 1)
            return;
        air[i][j] = 1;
        for (int idx = 0; idx < dx.length; idx++)
            dfs(i+dx[idx], j+dy[idx]);
    }

    // 2. 치즈가 녹는 시간 구하는 과정
    private static int meltingCheese() {
        int time = 0;
        boolean flag = false;
        while (!flag) {
            flag = true;
            air = new int[x][y];
            dfs(0, 0);
            for (int i = 0; i < cheese.length; i++) {
                for (int j = 0; j < cheese[0].length; j++) {
                    if (cheese[i][j] == 1) {
                        flag = false;
                        if (air[i + dx[0]][j + dy[0]] + air[i + dx[1]][j + dy[1]] + air[i + dx[2]][j + dy[2]] + air[i + dx[3]][j + dy[3]] >= 2)
                            cheese[i][j] = 0;
                    }
                }
            }
            time++;
        }
        return time-1;
    }

    // 4. 출력
    private static void outputTotal(int total) throws IOException {
        bw.write(String.valueOf(total));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        inputConfig();
        outputTotal(meltingCheese());
    }

}
