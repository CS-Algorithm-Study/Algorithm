package 구현;

/*
    풀이 방법 -> 높이(행)가 0인 지점부터 최대 높이까지 한 줄(행) 씩 물을 채우는 방식으로 풂
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14719 {
    static int W, H;
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        board = new int[H][W];
        visited = new boolean[H][W];

        st = new StringTokenizer(br.readLine());

        // 입력 받은 벽의 높이들을 2차원 배열로 생성하여 벽인 부분은 1, 비어 있는 부분은 0, 채워진 부분은 2로 채움
        for (int i = 0; i < W; i++) {
            int h = Integer.parseInt(st.nextToken());
            for (int j = 0; j < h; j++) {
                board[j][i] = 1;
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!visited[i][j] && board[i][j] == 0) {
                    fillWater(j, i);
                }
            }
        }

        System.out.println(getAmount());
    }

    // 한 줄 씩 물 채우기
    public static void fillWater(int x, int y) {
        boolean leak = false;
        // 지나온 칸들의 x 좌표를 저장
        List<Integer> pos = new ArrayList<>();

        // 만약 가장 왼쪽이 0이면 물이 누수되므로 벽을 만나기 전까지의 칸은 0(leak = true)
        if (x == 0 && board[y][x] == 0) leak = true;
        for (int i = x; i < W; i++) {
            if (board[y][i] == 1) break;
            visited[y][i] = true;
            // 가장 오른쪽까지 벽을 만나지 않았고, 가장 오른쪽이 0이면 지나온 칸들은 0
            if (i == W - 1 && board[y][i] == 0) {
                leak = true;
                break;
            }
            pos.add(i);
        }

        if (!leak) {
            // 물이 누수되지 않는 경우 지나온 칸들을 2로 채움
            for (Integer idx : pos) {
                board[y][idx] = 2;
            }
        }
    }

    // 고인 물의 총량 (2의 개수)를 구함
    public static int getAmount() {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0 ; j < W; j++) {
                if (board[i][j] == 2) {
                    count++;
                }
            }
        }
        return count;
    }
}
