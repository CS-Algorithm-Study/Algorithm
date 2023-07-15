import java.io.*;
import java.util.StringTokenizer;

// LIS
public class B_2352 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();
    private static int[] port;
    private static int[] LIS;
    private static int curMaxLink;

    // 1. port 정보, LIS 길이를 구할 array 초기화
    private static void input() throws IOException {
        int N = Integer.parseInt(br.readLine());
        port = new int[N];
        LIS = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            port[i] = Integer.parseInt(st.nextToken());
    }

    // 2-2. 이분탐색
    public static int lowerBound(int[] arr,  int value, int s, int e) {
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (value <= arr[mid]) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }
        return s;
    }

    // 2. LIS 최대 길이 구하는 과정
    private static int findAllCase() {
        int cnt = 0;
        LIS[cnt++] = port[0];
        for (int i = 1; i < port.length; i++) {
            if (LIS[cnt-1] < port[i])
                LIS[cnt++] = port[i];
            else {
                int idx = lowerBound(LIS, port[i], 0, cnt);
                LIS[idx] = port[i];
            }
        }
        for (int j = 0; j < LIS.length; j++) {
            if (LIS[j] == 0) {
                return j;
            }
        }
        return LIS.length;
    }

    /*
    private static int checkLink(int i, int maxPort, int link) {
        if (port[i] < maxPort)
            return link;
        link++;
        for (int j = i+1; j < port.length; j++) {
            int curLink = checkLink(j, port[i], link);
            if (j == port.length-1 && curLink > curMaxLink)
                curMaxLink = curLink;
        }
        return link;
    }

    private static int findAllCase() {
        int maxLink = 0;
        for (int i = 0; i < port.length; i++) {
            checkLink(i, 0, 0);
            if (curMaxLink > maxLink)
                maxLink = curMaxLink;
        }
        return maxLink;
    }
     */

    // 3. 출력
    private static void outputTotal(int total) throws IOException {
        bw.write(String.valueOf(total));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        outputTotal(findAllCase());
    }
}
