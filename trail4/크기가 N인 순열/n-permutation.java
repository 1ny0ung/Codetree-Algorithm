import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static boolean[] isUsed;
    static StringBuffer sb;
    static int[] permutation;
    static void make(int idx){
        if(idx == n){
            for(int p : permutation){
                sb.append(p).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= n; i++){
            if(isUsed[i]){
                continue;
            }
            permutation[idx] = i;
            isUsed[i] = true;
            make(idx+1);
            isUsed[i] = false;
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine().charAt(0) - '0';

        sb = new StringBuffer();
        isUsed = new boolean[n+1];
        permutation = new int[n];

        make(0);
        System.out.println(sb.toString());
    }
}