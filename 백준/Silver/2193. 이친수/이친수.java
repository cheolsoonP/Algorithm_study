import java.util.*;
import java.io.*; 
	
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		long[] DP = new long[N+1];
		
		for (int i=1;i<=N;i++) {
			if (i <= 2) {
				DP[i] = 1;
			} else {
				DP[i] = DP[i-1]+DP[i-2];
			}
		}
		System.out.println(DP[N]);
	}
}
