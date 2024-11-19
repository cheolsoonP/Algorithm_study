import java.io.*;
import java.util.*;

public class Main {

	static int N; 
	static int K; 
	static int[] coin; 
	static int[] dp; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(in.readLine()); 
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coin = new int[N];
		dp = new int[K+1];
		dp[0] = 1; 
		
		for (int i=0;i<N;i++) {
			coin[i] = Integer.parseInt(in.readLine());
			
			for (int j=coin[i];j<=K;j++) {
				dp[j] += dp[j-coin[i]];
			}
			//System.out.println(Arrays.toString(dp));
		}
		
		System.out.println(dp[K]);
		
	}

}
