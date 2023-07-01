package 그래프;

/*
    풀이 방법 -> 모든 방향의 조합을 구하여 재귀적으로 함수를 호출(백트래킹)하여 풀이함
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15683 {
    // 방향 클래스 -> dx는 열의 값(0 또는 1, -1중 하나), dy는 행의 값(0 또는 1, -1중 하나)
    static class Direct {
        int dx, dy;
        public Direct(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    // CCTV의 위치 클래스 -> x(열), y(행), d(CCTV번호)
    static class Pos {
        int x, y, d;
        public Pos(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static List<List<Direct[]>> direct = new ArrayList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static List<Pos> pos = new ArrayList<>();
    static int N, M, nx, ny;
    static Direct left, right, up, down;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] board = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num > 0 && num != 6) {
                    pos.add(new Pos(j, i, num));
                }
                board[i][j] = (char) (num +'0');
            }
        }
        setDirect();
        go(0, board);
        System.out.println(Collections.min(result));
    }

    // 재귀적으로 함수를 호출하는 부분
    public static void go(int idx, char[][] board) {
        // 만약 CCTV의 개수만큼 호출이 되었다면 그 시점의 사각지대 개수를 구함
        if (idx == pos.size()) {
            getBlindSpotCount(board);
            return;
        }
        // idx의 해당하는 CCTV의 pos를 이용하여 해당 CCTV 번호의 모든 방향 조합을 실행
        for (Direct[] dir : direct.get(pos.get(idx).d)) {
            char[][] copyBoard = new char[N][M];

            // 방향의 조합을 실행하기 위해 실행 전 board를 복사
            for (int i = 0 ; i < N; i++) {
                copyBoard[i] = board[i].clone();
            }

            // 감시 방향 채우기 실행
            for (Direct d : dir) {
                // 복사된 copyBoard를 넘겨주기 때문에 board는 이전 상태를 유지하여 다음 방향 조합도 문제 없이 실행 가능
                fill(pos.get(idx), d, copyBoard);
            }
            go(idx + 1, copyBoard);
        }
    }

    // 감시 방향을 채우는 함수
    public static void fill(Pos pos, Direct dir, char[][] board) {
        nx = pos.x;
        ny = pos.y;
        if (dir == left || dir == right) {
            for (int i = 0; i < M; i++) {
                nx += dir.dx;
                if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                    if (board[pos.y][nx] == '6') break;
                    if (board[pos.y][nx] != '0' && board[pos.y][nx] != '#') continue;
                    board[pos.y][nx] = '#';
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                ny += dir.dy;
                if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                    if (board[ny][pos.x] == '6') break;
                    if (board[ny][pos.x] != '0' && board[ny][pos.x] != '#') continue;
                    board[ny][pos.x] = '#';
                }
            }
        }
    }

    // 사각 지대를 구하는 함수
    public static void getBlindSpotCount(char[][] board) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '0') {
                    count += 1;
                }
            }
        }
        result.add(count);
    }

    // CCTV의 모든 방향 조합을 설정하는 함수
    public static void setDirect() {
        left = new Direct(-1, 0);
        right = new Direct(1, 0);
        up = new Direct(0, -1);
        down = new Direct(0, 1);
        direct.add(null);

        List<Direct[]> direct1 = new ArrayList<>();
        direct1.add(new Direct[]{left});
        direct1.add(new Direct[]{up});
        direct1.add(new Direct[]{right});
        direct1.add(new Direct[]{down});
        direct.add(direct1);

        List<Direct[]> direct2 = new ArrayList<>();
        direct2.add(new Direct[]{left, right});
        direct2.add(new Direct[]{up, down});
        direct.add(direct2);

        List<Direct[]> direct3 = new ArrayList<>();
        direct3.add(new Direct[]{left, up});
        direct3.add(new Direct[]{up, right});
        direct3.add(new Direct[]{down, right});
        direct3.add(new Direct[]{down, left});
        direct.add(direct3);

        List<Direct[]> direct4 = new ArrayList<>();
        direct4.add(new Direct[]{left, up, right});
        direct4.add(new Direct[]{up, right, down});
        direct4.add(new Direct[]{right, down, left});
        direct4.add(new Direct[]{down, left, up});
        direct.add(direct4);

        List<Direct[]> direct5 = new ArrayList<>();
        direct5.add(new Direct[]{left, up, right, down});
        direct.add(direct5);
    }
}
