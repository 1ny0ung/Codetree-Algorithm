import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] stuffs;
    static boolean[] isUsed;
    static int maxValue;
    static int n;
    static int m;
    static int c;

    // 시작점 주어지면 m열 범위 만큼에서 부분집합만큼의 무게 최대값 구하기
    static void carryMost(int startRow, int startCol, int idx){
        if(idx == m){
            int weight = 0;
            int value = 0;
            for(int i = 0; i < m; i++){
                if(isUsed[i]){
                    int s = stuffs[startRow][startCol + i];
                    weight += s;
                    value += s * s;
                }
                if(weight > c){
                    return;
                }
            }
            maxValue = Math.max(maxValue, value);
            return;
        }
        // 넣는 경우
        isUsed[idx] = true;
        carryMost(startRow, startCol, idx+1);
        // 안 넣는 경우
        isUsed[idx] = false;
        carryMost(startRow, startCol, idx+1);
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = st.nextToken().charAt(0) - '0';
        c = Integer.parseInt(st.nextToken());

        stuffs = new int[n][n];
        for(int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                stuffs[i][j] = st.nextToken().charAt(0) - '0';
            }
        }

        int[][] maxValues = new int[n][n-m+1];
        // 시작점에서 m열 무게까지 범위에서의 최댓값 미리 구하기
        for(int i = 0; i < n; i++){
            for (int j = 0; j <= n-m; j++) {
                isUsed = new boolean[m];
                maxValue = 0;
                carryMost(i, j, 0);
                maxValues[i][j] = maxValue;
            }
        }

        int maxAns = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n-m; j++) {
                for (int i2 = 0; i2 < n; i2++){
                    for (int j2 = 0; j2 <= n-m; j2++) {
                        if(i == i2 && j2 < j+m){
                            continue;
                        }
                        int ans = maxValues[i][j] + maxValues[i2][j2];
                        maxAns = Math.max(ans, maxAns);
                    }
                }
            }
        }
        System.out.println(maxAns);
    }
}
