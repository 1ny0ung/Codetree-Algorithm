import java.util.*;
import java.io.*;
public class Main {
    static int n;
    static StringBuffer sb;
    static boolean[] isUsed;
    static int[] permutation;

    static void makeReversed(int idx){
        if(idx == n){
            for(int p : permutation){
                sb.append(p).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = n; i >= 1; i--){
            if(isUsed[i]){
                continue;
            }
            permutation[idx] = i;
            isUsed[i] = true;

            makeReversed(idx+1);
            isUsed[i] = false;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine().charAt(0) - '0';

        isUsed = new boolean[n+1];
        permutation = new int[n];
        sb = new StringBuffer();

        makeReversed(0);
        System.out.println(sb.toString());
    }
}