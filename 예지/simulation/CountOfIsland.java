package 예지.simulation;
/*
* [문제]
* 정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성
* - 정사각형, 가로, 세로, 대각선으로 연결된 곳은 걸어갈 수 있음
* - w, h는 50보다 작거나 같은 양의 정수
* - 둘째 줄부터 h개 줄에는 지도가 주어짐, 1 : 땅, 0 : 바다
* - 입력 마지막 줄에는 0이 두 개
* */

/**
 * 한 칸씩 검증하기, 동서남북에 1이 있으면 이동(대각선도) + 이동 전 위치 0으로 바꾸기
 * 더이상 없으면 그 다음 칸으로 이동
 * */

import java.util.Scanner;

public class CountOfIsland {
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; // 서북, 북, 동북, 서, 동, 서남, 남, 동남 방향
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public boolean is_range(int x, int y, int w, int h) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }

    public void blank(int[][] map, int x, int y, int w, int h) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (is_range(nx, ny, w, h) && map[nx][ny] == 1) {
                map[nx][ny] = 0;
                blank(map, nx, ny, w, h);
            }
        }
    }

    public void solution(int[][] map, int w, int h) {
        int answer = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 1) {
                    answer++;
                    blank(map, i, j, w, h);
                }
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        CountOfIsland C = new CountOfIsland();
        Scanner input = new Scanner(System.in);
        while (true) {
            int w = input.nextInt();
            int h = input.nextInt(); // w : 가로, h : 세로
            if (w == 0 && h == 0) break;
            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    map[i][j] = input.nextInt();
                }
            }
            C.solution(map, w, h);
        }
    }
}