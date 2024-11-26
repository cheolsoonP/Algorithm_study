import java.io.*;
import java.util.*;

public class Main {
	
	static int[] DP; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		int N = Integer.parseInt(in.readLine());
		DP = new int[1000001]; 
		DP[0] = 0; 
		DP[1] = 1; 
		DP[2] = 2; 
		DP[3] = 3; 
		if (N <= 3) {
			System.out.println(DP[N]);
			return; 
		}
		
		for (int i=4;i<=N;i++) {
			DP[i] = DP[i-1] + DP[i-2];
			DP[i] = DP[i] % 15746;
		}
		System.out.println(DP[N]);
		return;
	}
	
}
