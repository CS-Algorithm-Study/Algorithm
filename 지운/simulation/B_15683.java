import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B_15683 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    static int x, y;
    static int[][] maps;
    static int[][] visited;
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};

    // 1. x, y 입력
    private static void inputXY() throws IOException {
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
    }

    // 2. maps 채우기
    private static void inputMap() throws IOException {
        visited = new int[x][y];
        maps = new int[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 3-2. index가 배열 범위 안이면서 maps[idx]가 벽이 아닌 경우 감시 가능(true), 불가(false)
    private static boolean isPossible(int i, int j) {
        return i >= 0 && i < x && j >= 0 && j < y && maps[i][j] != 6;
    }

    // 3-1. cctv 별로 뻗어나갈 수 있는 구간 다르게 설정, visited table 만들어서 nextQueue에 추가하는 과정
    private static LinkedList<int[][]> addLocation(int i, int j) {
        int ii, jj;
        LinkedList<int[][]> nextQueue = new LinkedList<>();
        int[][] next;
        if (maps[i][j] == 1) {
            for (int idx = 0; idx < 4; idx++) {
                next = new int[x][y];
                ii = i; jj = j;
                while (isPossible(ii, jj)) {
                    next[ii][jj] = 1;
                    ii += dx[idx];
                    jj += dy[idx];
                }
                nextQueue.add(next);
            }
        }
        else if (maps[i][j] == 2) {
            for (int idx = 0; idx < 2; idx++) {
                next = new int[x][y];
                ii = i; jj = j;
                while (isPossible(ii, jj)) {
                    next[ii][jj] = 1;
                    ii += dx[idx];
                    jj += dy[idx];
                }
                ii = i; jj = j;
                while (isPossible(ii, jj)) {
                    next[ii][jj] = 1;
                    ii += dx[idx+2];
                    jj += dy[idx+2];
                }
                nextQueue.add(next);
            }
        }
        else if (maps[i][j] == 3) {
            for (int idx = 0; idx < 4; idx++) {
                next = new int[x][y];
                ii = i; jj = j;
                while (isPossible(ii, jj)) {
                    next[ii][jj] = 1;
                    ii += dx[idx];
                    jj += dy[idx];
                }
                ii = i; jj = j;
                while (isPossible(ii, jj)) {
                    next[ii][jj] = 1;
                    ii += dx[(idx+1)%4];
                    jj += dy[(idx+1)%4];
                }
                nextQueue.add(next);
            }
        }
        else if (maps[i][j] == 4) {
            for (int idx = 0; idx < 4; idx++) {
                next = new int[x][y];
                ii = i; jj = j;
                while (isPossible(ii, jj)) {
                    next[ii][jj] = 1;
                    ii += dx[idx];
                    jj += dy[idx];
                }
                ii = i; jj = j;
                while (isPossible(ii, jj)) {
                    next[ii][jj] = 1;
                    ii += dx[(idx+1)%4];
                    jj += dy[(idx+1)%4];
                }
                ii = i; jj = j;
                while (isPossible(ii, jj)) {
                    next[ii][jj] = 1;
                    ii += dx[(idx+2)%4];
                    jj += dy[(idx+2)%4];
                }
                nextQueue.add(next);
            }
        }
        else {
            next = new int[x][y];
            for (int idx = 0; idx < 4; idx++) {
                ii = i; jj = j;
                while (isPossible(ii, jj)) {
                    next[ii][jj] = 1;
                    ii += dx[idx];
                    jj += dy[idx];
                }
            }
            nextQueue.add(next);
        }
        return nextQueue;
    }

    // 3-3. 현재 감시 가능한 구역 case 1개와 다음 감시 가능한 구역 case 1개를 병합
    private static int[][] sumCurAndNext(int[][] a, int[][] b) {
        int[][] result = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (a[i][j] == 1 || b[i][j] == 1)
                    result[i][j] = 1;
            }
        }
        return result;
    }

    // 4. { 전체 size } - { 최종 case들 중 가장 visited 감시가 많이 가능한 (1로 많이 채워진) 경우의 최댓값 } - { 벽의 개수 }
    private static int getMinBlank(LinkedList<int[][]> queue) {
        int maxLook = 0;
        while (queue.size() > 0) {
            int[][] result = queue.poll();
            int total = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (result[i][j] == 1 || maps[i][j] == 6)
                        total += 1;
                }
            }
            if (maxLook < total)
                maxLook = total;
        }
        return x * y - maxLook;
    }

    // 3. map 전수조사, cctv를 찾으면 가능한 경우들에 대해 visited table 제작(possibleLocations)
    // 현재 가능한 경우들에 대한 visited table과 cartesian product 수행(비효율적이긴 한데..)
    private static void checkAllCase() {
        LinkedList<int[][]> queue = new LinkedList<>();
        LinkedList<int[][]> nextQueue = new LinkedList<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (maps[i][j] > 0 && maps[i][j] < 6) {
                    LinkedList<int[][]> possibleLocations = addLocation(i, j);
                    if (queue.size() == 0)
                        queue = (LinkedList<int[][]>) possibleLocations.clone();
                    else {
                        while (queue.size() > 0) {
                            int[][] cur = queue.poll();
                            for (int[][] possibleLocation : possibleLocations) {
                                nextQueue.add(sumCurAndNext(cur, possibleLocation));
                            }
                        }
                        queue = (LinkedList<int[][]>) nextQueue.clone();
                    }
                }
            }
        }
        // 반례 늦게 찾은 부분. cctv가 아예 없는 경우 기본 visited 추가
        if (queue.size() == 0)
            queue.add(visited);
        outputTotal(getMinBlank(queue));
    }

    // 5. 결과 출력
    private static void outputTotal(int total) {
        System.out.println(total);
    }

    public static void main(String[] args) throws IOException {
        inputXY();
        inputMap();
        checkAllCase();
    }

}
