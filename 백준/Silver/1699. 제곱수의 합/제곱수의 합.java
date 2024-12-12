import java.util.*;
import java.io.*;
 
public class Main {
	
	static int[] DP; 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		DP = new int[N+1];
		for (int i=1; i<=N;i++) {
			DP[i] = i; 
		}
		for (int i=1; i<=Math.sqrt(N);i++) {
			DP[i*i] = 1; 
		}
		for (int i=2;i<=N;i++) {
			for (int j=1;j<=i/2;j++) {
				DP[i] = Math.min(DP[i], DP[j] + DP[i-j]); 
			}
		}
		
		System.out.println(DP[N]);	
	}
}
