package 백트레킹;

/*
    풀이 방법 -> 백트레킹을 통해 M개의 치킨집 조합을 모두 구하고, 공식을 이용하여 최소 거리를 구함
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15686 {

    static class Pos {
        int x, y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int N, M, minDist;
    public static List<Pos> chicken;
    public static List<Pos> home;
    public static Pos[] comb;
    public static boolean[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chicken = new ArrayList<>();
        home = new ArrayList<>();
        comb = new Pos[M];
        minDist = Integer.MAX_VALUE;

        // 집과 치킨의 좌표를 각각 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    chicken.add(new Pos(j, i));
                } else if (num == 1) {
                    home.add(new Pos(j, i));
                }
            }
        }
        selected = new boolean[chicken.size()];
        findChickenComb(0, 0);
        System.out.println(minDist);
    }

    // M개 치킨 집의 모든 조합 찾기
    public static void findChickenComb(int idx, int depth) {
        if (depth == M) {
            getMinDist();
            return;
        }
        for (int i = idx; i < chicken.size(); i++) {
            if (!selected[i]) {
                selected[i] = true;
                comb[depth] = chicken.get(i);
                findChickenComb(i + 1, depth + 1);
                selected[i] = false;
            }
        }
    }

    // 각각의 집 좌표를 치킨집마다 거리를 비교하여 가장 작은 값을 구하고 저장
    public static void getMinDist() {
        int dist = 0;
        int temp, total = 0;
        for (Pos homePos : home) {
            dist = Integer.MAX_VALUE;
            for (Pos chickenPos : comb) {
                temp = Math.abs(homePos.x - chickenPos.x) + Math.abs(homePos.y - chickenPos.y);
                if (dist > temp) dist = temp;
            }
            total += dist;
        }
        // 해당 치킨집 조합의 최소 거리가 이전 값들보다 작으면 대입
        if (minDist > total) minDist = total;
    }
}
