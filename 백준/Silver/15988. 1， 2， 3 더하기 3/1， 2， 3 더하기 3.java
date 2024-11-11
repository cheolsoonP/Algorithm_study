import java.io.*;
import java.util.*;

public class Main {
	static long[] DP; 
	static int N; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		/*
		 * DP[i] = DP[i-3] + DP[i-2] + DP[i-1] 
		 * DP[1] = 1 
		 * DP[2] = 2 
		 * DP[3] = 4 
		 * */
		
		DP = new long[1000002];
		for (int i=1;i<=1000000;i++) {
			if (i == 1) {
				DP[i] = 1;
			} else if (i==2) {
				DP[i] = 2;
			} else if (i==3) {
				DP[i] = 4; 
			} else {
				DP[i] = (DP[i-1]+DP[i-2]+DP[i-3]) % 1000000009;
			}
		}
		
		N = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			int num = Integer.parseInt(in.readLine());			
			sb.append(DP[num]+"\n");
		}	
		System.out.println(sb.toString());
	}

}
