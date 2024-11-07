import java.util.*; 
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// P[N] = P[N-2] + P[N-3] 
		// P[0,1,2] = 1 
		long[] P = new long[101];
		P[1] = 1; 
		P[2] = 1; 
		P[3] = 1;
		for (int i=4;i<101;i++) {
			P[i] = P[i-2] + P[i-3];
		}
		
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc=1;tc<=T;tc++) {
			int N = Integer.parseInt(in.readLine());
			sb.append(P[N]+"\n");
		}
		System.out.println(sb.toString());		
	}
}
