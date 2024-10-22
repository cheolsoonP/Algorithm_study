import java.util.*;
import java.io.*; 

public class Main {
	static int N, M; 
	static int DP[];
	/*
	 * DP[i] = 현재까지 합 
	 * DP[i] - DP[j] = i~j까지 합 
	 * */
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		st = new StringTokenizer(in.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		DP = new int[N+1];
		st = new StringTokenizer(in.readLine());
		for (int i=1;i<=N;i++) {
			DP[i] = DP[i-1] + Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder(); 
		int a,b;
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine()); 
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(DP[b] - DP[a-1]).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	

}
