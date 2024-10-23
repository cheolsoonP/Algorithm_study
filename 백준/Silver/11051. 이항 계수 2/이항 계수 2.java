import java.util.*;
import java.io.*;

public class Main {
	static int N, K; 
	static int[][] DP; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		DP = new int[1001][1001];
		
		/*
		 * nCr = n-1Cr-1 + n-1Cr
		 * DP[i][j] = DP[i-1][j-1] + DP[i-1][j] 
		 * nCr = n!/r! 
		 * */
		
		for (int i=1;i<=1000;i++) {
			DP[i][0] = 1;
			DP[i][i] = 1;
			for (int j=1;j<i;j++) {
				DP[i][j] = (DP[i-1][j] + DP[i-1][j-1]) % 10007;
			}
		}
		
		System.out.println(DP[N][K]);
	}
}
