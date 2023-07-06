import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_15686 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int M;
    private static int result = Integer.MAX_VALUE;
    private static int[][] storeDistance;
    private static final ArrayList<int[]> houseAt = new ArrayList<>();
    private static final ArrayList<int[]> storeAt = new ArrayList<>();
    private static boolean[] visited;

    // 1. 집 좌표, 가게 좌표 저장
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1)
                    houseAt.add(new int[]{i, j});
                else if (num == 2)
                    storeAt.add(new int[]{i, j});
            }
        }
    }

    // 3. 백트래킹을 이용, 모든 조합의 가게에 대해 치킨 거리 최솟값 갱신
    static void combination(int[][] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            // clone을 생활하자
            int[] minDistances = arr[start-1].clone();
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    int[] compare = arr[i];
                    for (int j = 0; j < compare.length; j++) {
                        if (compare[j] < minDistances[j])
                            minDistances[j] = compare[j];
                    }
                }
            }
            int minTotal = 0;
            for (int minDistance : minDistances)
                minTotal += minDistance;
            if (minTotal < result)
                result = minTotal;
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    // 2. 가게를 기준으로, 집과의 거리를 저장
    private static void fillStoreDistance() {
        storeDistance = new int[storeAt.size()][houseAt.size()];
        for (int i = 0; i < storeAt.size(); i++) {
            for (int j = 0; j < houseAt.size(); j++)
                storeDistance[i][j] = Math.abs(storeAt.get(i)[0] - houseAt.get(j)[0]) + Math.abs(storeAt.get(i)[1] - houseAt.get(j)[1]);
        }
        visited = new boolean[storeDistance.length];
    }

    public static void main(String[] args) throws IOException {
        input();
        fillStoreDistance();
        combination(storeDistance, visited, 0, storeAt.size(), M);
        bw.write(String.valueOf(result));
        bw.flush();
    }
}
