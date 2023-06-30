import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_14719 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    static int x, y;
    static int[] world;

    // 1. x, y, world 입력
    private static void inputXY() throws IOException {
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        world = new int[y];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < y; i++) {
            world[i] = Integer.parseInt(st.nextToken());
        }
    }

    // 2. 고인 빗물의 양 구하기
    // 가장 높은 크기부터 낮은 크기까지, world를 돌며 현재 관측하는 층 이상 블록이 쌓여있는 인덱스(y좌표) list에 추가
    // list[n+1] - list[n] - 1 => 해당 층에서 가장 가까이 떨어져있는 두 블록 사이의 칸 갯수(=빗물이 고이게 될 현재 층의 칸 갯수)
    // list의 모든 index에 대해 위 식 적용시켜서 총 고인 빗물 양 합(total)에 추가
    private static int getRainTotal() {
        int total = 0;
        ArrayList<Integer> BlockIdx;
        while (x > 0) {
            BlockIdx = new ArrayList<>();
            for (int i = 0; i < y; i++) {
                if (world[i] >= x)
                    BlockIdx.add(i);
            }
            x--;
            if (BlockIdx.size() < 2)
                continue;
            for (int j = 0; j < BlockIdx.size() - 1; j++) {
                total += (BlockIdx.get(j+1) - BlockIdx.get(j) - 1);
            }
        }
        return total;
    }

    // 3. 결과 출력
    private static void outputTotal(int total) {
        System.out.println(total);
    }

    public static void main(String[] args) throws IOException {
        inputXY();
        outputTotal(getRainTotal());
    }
}
