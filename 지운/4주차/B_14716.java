import java.io.*;
import java.util.StringTokenizer;

public class B_14716 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    static int x, y;
    static int[][] banner;
    static boolean[][] visited;
    static int[] dx = new int[] {0, 1, 0, -1, 1, -1, 1, -1};
    static int[] dy = new int[] {1, 0, -1, 0, 1, 1, -1, -1};

    // 1. x, y, banner 입력
    private static void inputConfig() throws IOException {
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        visited = new boolean[x][y];
        banner = new int[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++)
                banner[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    // 3. dfs
    private static void dfs(int i, int j) {
        if (i < 0 || i >= banner.length || j < 0 || j >= banner[0].length || visited[i][j] || banner[i][j] == 0)
            return;
        visited[i][j] = true;
        for (int idx = 0; idx < dx.length; idx++)
            dfs(i+dx[idx], j+dy[idx]);
    }

    // 2. 글자 갯수 구하는 과정
    private static int checkLetters() {
        int letters = 0;
        for (int i = 0; i < banner.length; i++) {
            for (int j = 0; j < banner[0].length; j++) {
                if (!visited[i][j] && banner[i][j] == 1) {
                    letters++;
                    dfs(i, j);
                }
            }
        }
        return letters;
    }

    // 4. 출력
    private static void outputTotal(int total) throws IOException {
        bw.write(String.valueOf(total));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        inputConfig();
        outputTotal(checkLetters());
    }
}
