import java.io.*;
import java.util.*; 

public class Main {
	static int N; 
	static int[] DP; 
	static int[] arr;
	static int result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder(); 
	
		int N = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		DP = new int[N];
		arr = new int[N];
		for (int i=0;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			DP[i] = num; // 초기화 
			arr[i] = num; 
		}
		
		result = DP[0];
		// DP[i] = max(DP[i], DP[j]+A[i]) 
		for (int i=1;i<N;i++) {
			for (int j=0;j<i;j++) {
				if (arr[j] < arr[i]) {
					DP[i] = Math.max(DP[i], DP[j]+arr[i]);
				}
			}
			result = Math.max(DP[i], result);
		}
		System.out.println(result);
	}
}
