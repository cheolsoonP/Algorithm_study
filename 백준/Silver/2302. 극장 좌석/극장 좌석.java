import java.io.*;
import java.util.*;

public class Main {
	static int N; 
	static int M; 
	static int beforeVIP = 0; 
	static long[] DP; 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		/*
		 * DP[i] = DP[i-1]+DP[i-2] 
		 * VIP를 제외하고 각 
		 * 1~VIP1-1 * VIP1+1~VIP2-1 ... 
		 * */
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		DP = new long[41];
		DP[0] = 1; 
		DP[1] = 1; 
		DP[2] = 2; 
		for (int i=3;i<=40;i++) {
			DP[i] = DP[i-1]+DP[i-2];
		}
		
		long result = 1; 
		for (int i=0;i<M;i++) {
			int currVIP = Integer.parseInt(in.readLine());
			result *= DP[currVIP - beforeVIP -1];
			beforeVIP = currVIP;
		}
		result *= DP[N-beforeVIP];
		
		System.out.println(result);
		
	}

}
