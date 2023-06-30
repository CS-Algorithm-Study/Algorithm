import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B_2178 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    static int x, y;
    static int[][] maps;
    static int[][] visited;
    static int[] dx = new int[] {0, 1, -1, 0};
    static int[] dy = new int[] {1, 0, 0, -1};

    // 1. x, y 입력
    private static void inputXY() throws IOException {
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
    }

    // 2. maps 채우기, 이 때 이후에 특정칸의 인접칸을 모두 확인할 것이므로 이동할 수 없는 칸이 0이라는 조건을 이용해 zero padding
    private static void inputMapWithZeroPadding() throws IOException {
        visited = new int[x+2][y+2];
        maps = new int[x+2][y+2];
        for (int i = 1; i < x+1; i++) {
            String str = br.readLine();
            for (int j = 1; j < y+1; j++) {
                maps[i][j] = Integer.parseInt(String.valueOf(str.charAt(j-1)));
            }
        }
    }

    private static boolean isWay(int i, int j) {
        return maps[i][j] == 1 && visited[i][j] == 0;
    }

    // 3. BFS 이용
    private static int checkMinWay(int i, int j) {
        int total = 0;
        LinkedList<Integer[]> queue = new LinkedList<>();
        LinkedList<Integer[]> nextQueue;
        boolean flag = false;
        visited[i][j] = 1;
        queue.add(new Integer[] {i, j});

        // 한 단계가 끝날 때마다 모든 인접 칸들에 대한 정보를 담고 있는 다음 단계(nextQueue)를 현재 queue에 복사
        while (queue.size() > 0) {
            nextQueue = new LinkedList<>();
            while (queue.size() > 0) {
                Integer[] candidate = queue.poll();
                // 목적지 찾았을 경우 탐색종료
                if (candidate[0] == x && candidate[1] == y) {
                    flag = true;
                    break;
                }
                int idx = 0;
                while (idx < 4) {
                    if (isWay(candidate[0] + dx[idx], candidate[1] + dy[idx])) {
                        visited[candidate[0] + dx[idx]][candidate[1] + dy[idx]] = 1;
                        nextQueue.add(new Integer[]{candidate[0] + dx[idx], candidate[1] + dy[idx]});
                    }
                    idx += 1;
                }
            }

            queue = (LinkedList<Integer[]>) nextQueue.clone();
            total += 1;
            if (flag)
                break;
        }

        return total;
    }

    // 5. 결과 출력
    private static void outputTotal(int total) {
        System.out.println(total);
    }

    public static void main(String[] args) throws IOException {
        inputXY();
        inputMapWithZeroPadding();
        outputTotal(checkMinWay(1, 1));
    }
}
