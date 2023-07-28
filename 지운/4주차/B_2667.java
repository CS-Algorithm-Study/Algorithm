import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class B_2667 {

    private static int check(int x, int y, String[][] map, boolean[][] isVisited, int totalVisited) {
        if (x < 0 || x >= map.length || y < 0 || y >= map.length || isVisited[x][y] || Objects.equals(map[x][y], "0"))
            return totalVisited;
        isVisited[x][y] = true;
        totalVisited++;
        totalVisited = check(x-1, y, map, isVisited, totalVisited);
        totalVisited = check(x, y-1, map, isVisited, totalVisited);
        totalVisited = check(x+1, y, map, isVisited, totalVisited);
        totalVisited = check(x, y+1, map, isVisited, totalVisited);
        return totalVisited;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String[][] map = new String[N][N];
        boolean[][] isVisited = new boolean[N][N];
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            map[i] = input;
        }
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                int totalVisited = check(j, k, map, isVisited, 0);
                if (totalVisited > 0)
                    result.add(totalVisited);
            }
        }
        result.sort(Comparator.naturalOrder());
        bw.write(String.valueOf(result.size()));
        bw.write("\n");
        for (Integer num : result) {
            bw.write(String.valueOf(num));
            bw.write("\n");
        }
        bw.flush();
    }
}
