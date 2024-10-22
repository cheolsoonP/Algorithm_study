import java.util.*;
import java.io.*; 

public class Main {
	static int N; 
	static int[] DP;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		DP = new int[1001];
		DP[1] = 1;
		DP[2] = 2;
		for (int i=3;i<=1000;i++) {
			DP[i] = (DP[i-1]+DP[i-2])%10007;
		}
		System.out.println(DP[N]);
		
	}
}
