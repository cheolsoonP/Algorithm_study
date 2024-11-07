import java.util.*; 
import java.io.*; 

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		int N = Integer.parseInt(in.readLine());
		int[] T = new int[21];
		int[] P = new int[21];
		int[] DP = new int[21];
		
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(in.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0; 
		for (int i=N;i>=1;i--) {
			if (i+T[i]-1 > N) {
				DP[i] = DP[i+1];
			} else {
				DP[i] = Math.max(DP[i+T[i]] + P[i], DP[i+1]);
			}
			result = Math.max(DP[i], result);
		}
		System.out.println(result);		
	}
}
