import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.IntPredicate;

class Combination {
    private int n;
    private int r;
    private int[] now; // 현재 조합
    private ArrayList<ArrayList<Integer>> result; // 모든 조합

    public ArrayList<ArrayList<Integer>> getResult() {
        return result;
    }

    public Combination(int n, int r) {
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<ArrayList<Integer>>();
    }

    public void combination(int[] arr, int depth, int index, int target) {
        if (depth == r) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j : now)
                temp.add(arr[j]);
            result.add(temp);
            return;
        }
        if (target == n) return;
        now[index] = target;
        combination(arr, depth + 1, index + 1, target + 1);
        combination(arr, depth, index, target + 1);
    }
}

public class B_14889 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] S;

    private static void setS (int N) throws IOException {
        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                S[i][j] = Integer.parseInt(st.nextToken());
        }
    }


    private static int getDiff(int[] teamCase, int diff) {
        for (int idxFirst : teamCase) {
            for (int idxSecond : teamCase)
                diff += S[idxFirst][idxSecond];
        }
        return diff;
    }

    private static int getMinDiff() {
        int minDiff = 990;
        int[] combIdx = new int[S.length - 1];

        for (int idx = 0; idx < combIdx.length; idx++)
            combIdx[idx] = idx + 1;
        Combination startTeamIdxWithoutZero = new Combination(combIdx.length, combIdx.length / 2);
        startTeamIdxWithoutZero.combination(combIdx, 0, 0, 0);

        ArrayList<ArrayList<Integer>> result = startTeamIdxWithoutZero.getResult();
        int[][] startTeamIdx = new int[result.size()][S.length / 2];
        for (int i = 0; i < result.size(); i++) {
            startTeamIdx[i][0] = 0;
            for (int idx = 0; idx < combIdx.length / 2; idx++)
                startTeamIdx[i][idx + 1] = result.get(i).get(idx);
        }

        for (int[] startTeamCase : startTeamIdx) {
            int diff = 0;
            int[] linkTeamCase = new int[S.length / 2];
            int idx = 0;
            for (int linkIdx = 1; linkIdx < S.length; linkIdx++) {
                int finalLinkIdx = linkIdx;
                if (Arrays.stream(startTeamCase).noneMatch(value -> value == finalLinkIdx)) {
                    linkTeamCase[idx] = linkIdx;
                    idx++;
                }
            }

            diff = getDiff(startTeamCase, diff);
            diff = getDiff(linkTeamCase, -diff);
            diff = Math.abs(diff);
            if (diff < minDiff)
                minDiff = diff;
        }
        return minDiff;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        setS(N);
        System.out.println(getMinDiff());
    }
}
