import java.io.*;
import java.util.*; 

public class Main {
	static int N; 
	static int[] DP; 
	static int[] PRE;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		DP = new int[1000000+1];
		PRE = new int[1000000+1];
		
		DP[1] = 0; 
		for (int i=2;i<=N;i++) {
			DP[i] = DP[i-1]+1;
			PRE[i] = i-1;

			if (i%2==0 && DP[i] > DP[i/2]+1) {//2의 배수
				DP[i] = DP[i/2]+1;
				PRE[i] = i/2;
			}
			if (i%3==0 && DP[i] > DP[i/3]+1) {//3의 배수 
				DP[i] = DP[i/3]+1;
				PRE[i] = i/3;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(DP[N]).append("\n");
		int pos = N;
		while(true) {
			sb.append(pos+" ");
			if (pos <= 1) break;
			pos = PRE[pos];
		}
		
		System.out.println(sb.toString());
		
	}
}
