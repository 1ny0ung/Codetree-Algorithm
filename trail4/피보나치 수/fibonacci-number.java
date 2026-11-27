import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] piboNums;
    public static int pibo(int n){
        if(piboNums[n] != -1){
            return piboNums[n];
        }
        if(n <= 2){
            piboNums[n] = 1; 
        }
        else{
            piboNums[n] = pibo(n-2) + pibo(n-1);
        }
        return piboNums[n];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        piboNums = new int[n+1];
        Arrays.fill(piboNums, -1);

        System.out.println(pibo(n));
    }
}