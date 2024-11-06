import java.util.*;
import java.io.*; 

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		/*
		 * N개 정수로 임의 수열 
		 * 이중 연속된 몇 개 수선택해서 가장 큰 합 
		 * DP[i] = 현재까지 더했을 때 최대값 
		 * DP[i] = Math.max(DP[i], DP[i-1]+DP[i]) 
		 * */
		int N = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		long[] DP = new long[N];		
		for (int i=0;i<N;i++) {
			DP[i] = Long.parseLong(st.nextToken());
		}
		
		long result = DP[0]; 
		for (int i=1;i<N;i++) {
			DP[i] = Math.max(DP[i], DP[i]+DP[i-1]);
			result = Math.max(DP[i], result);
		}
		System.out.println(result);		
	}
}
