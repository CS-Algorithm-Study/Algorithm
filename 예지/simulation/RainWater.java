package 예지.simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RainWater {
    static int H, W;
    static int[][] map;
    static int answer = 0;

    static class Point {
        int x, y, type;

        public Point(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public int solution() {

        // 아래부터 순회하면서 Queue에 넣음 -> 한 줄씩 채우기
        for (int i = H - 1; i >= 0; i--) {
            Queue<Point> Q = new LinkedList<>();
            for (int j = 0; j < W; j++) {
                // 만약에 블럭을 마주치고 Queue가 비어있지 않다면 큐 안에 1을 마주치기 전까지는 패스, 1을 마주치면 현재 블럭과 사이에 있는 공간을 빗물로 채움
                // 진짜 너무 비효율적인 것 같다...ㅋㅋㅋㅋ
                if (map[i][j] == 1 && !Q.isEmpty()) {
                    boolean check = false;
                    while (!Q.isEmpty()) {
                        Point tmp = Q.poll();
                        if (tmp.type == 1) {
                            check = true;
                            continue;
                        }
                        if (check) {
                            map[tmp.x][tmp.y] = -1;
                        } else {
                            continue;
                        }
                    }
                }
                Q.offer(new Point(i, j, map[i][j]));
            }
        }

        // 빗물 양 구하기
        for (int i = 0; i < H; i++ ) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == -1) {
                    answer ++;
                }
            }
        }
        return answer;

    }

    public static void main(String[] args) {
        RainWater R = new RainWater();
        Scanner input = new Scanner(System.in);
        H = input.nextInt();
        W = input.nextInt();
        map = new int[H][W];

        // 블럭이 있는 곳을 1로 채움, 0이 빈 공간
        for (int i = 0; i < W; i++) {
            int block = H - input.nextInt() ;
            for (int j = block ; j < H; j++) {
                map[j][i] = 1;
            }
        }
        System.out.println(R.solution());
    }
}
