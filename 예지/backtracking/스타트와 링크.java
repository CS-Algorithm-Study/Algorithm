package 예지.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] map;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void solution() {
        int start = 0;
        int link = 0;
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                if(visited[i] && visited[j]){
                    start += map[i][j];
                    start += map[j][i];
                }
                else if(!visited[i] && !visited[j]){
                    link += map[i][j];
                    link += map[j][i];
                }
            }
        }
        min = Math.min(min, Math.abs(start-link));
    }

    public static void dfs(int index, int cnt){
        if(cnt == N/2){
            solution();
        }

        for(int i = index; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i + 1, cnt+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        map = new int[N][N];
        visited = new boolean[N];
        for(int i = 0; i < N; i++){
            tokens = new StringTokenizer(input.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        dfs(0,0);
        System.out.println(min);
    }

}
