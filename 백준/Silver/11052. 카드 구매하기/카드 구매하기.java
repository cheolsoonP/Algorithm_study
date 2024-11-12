import java.io.*;
import java.util.*;

public class Main {

	static int N; 
	static int[] P; 
	static int[] DP; 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		/*
		 * DP[i] = 
		 * 		P[1] + DP[i-1] 
		 * 		P[2] + DP[i-2] 
		 * 
		 * DP[1] = P[1] + DP[1-1] 
		 * DP[2] = Math.max(
		 * ...			
		 * */
		
		N = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		P = new int[N+1];
		DP = new int[N+1]; 
		
		for (int i=1;i<=N;i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=i;j++) {
				DP[i] = Math.max(P[j] + DP[i-j], DP[i]);
			}
		}
		System.out.println(DP[N]);	
	}

}
