package 백트레킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {
    public static int N;
    public static int[][] synergy;
    public static int[] startTeam;
    public static int[] linkTeam;
    public static boolean[] selected;
    public static int min, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        synergy = new int[N][N];
        startTeam = new int[N / 2];
        linkTeam = new int[N / 2];
        selected = new boolean[N];
        min = 100;
        count = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                synergy[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        findMinDiff(0);
        System.out.println(min);
    }


    public static void findMinDiff(int idx) {
        // 스타트 팀이 다 차면
        if (count == N / 2) {
            int i = 0;
            // 스타트 팀이 아닌 사람은 모두 링크 팀
            for (int j = 0; j < N; j++) {
                if (!selected[j]) {
                    linkTeam[i] = j;
                    i++;
                }
            }
            int diff = Math.abs(getTotalSynergy(startTeam) - getTotalSynergy(linkTeam));
            if (min > diff) min = diff;
            return;
        }

        for (int i = idx; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                startTeam[count] = i;
                count += 1;
                findMinDiff(i + 1);
                selected[i] = false;
                count -= 1;
            }
        }
    }

    public static int getTotalSynergy(int[] team) {
        int total = 0;
        for (int i = 0; i < team.length; i++) {
            for (int j = i + 1; j < team.length; j++) {
                total += synergy[team[i]][team[j]] + synergy[team[j]][team[i]];
            }
        }
        // 능력치 차이가 0이라면 이보다 작은 최소 값은 없으므로, 0을 출력하고 재귀함수를 모두 종료
        if (total == 0) {
            System.out.println(total);
            System.exit(0);
        }
        return total;
    }
}