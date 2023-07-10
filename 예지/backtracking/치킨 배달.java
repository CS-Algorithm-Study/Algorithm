package 예지.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Chicken {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] map;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    static List<Point> chicken = new ArrayList<>();
    static List<Point> home = new ArrayList<>();

    public static class Point{
        int r;
        int c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public void solution(int index, int cnt){
        if(cnt == M){
            int sum = 0;
            for (int i = 0; i < home.size(); i++) {
                int dist = 0;
                int fin = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        dist = Math.abs(home.get(i).r - chicken.get(j).r) + Math.abs(home.get(i).c - chicken.get(j).c);
                        if(dist < fin){
                            fin = dist;
                        }
                    }
                }
                sum += fin;
            }
            min = Math.min(min, sum);
            return;
        }
        for(int i = index; i < chicken.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                solution(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        Chicken C = new Chicken();
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            tokens = new StringTokenizer(input.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == 1) {
                    home.add(new Point(i, j));
                }
                if(map[i][j] == 2){
                    chicken.add(new Point(i, j));
                }
            }
        }
        visited = new boolean[chicken.size()];
        C.solution(0,0);
        System.out.println(min);
    }
}
