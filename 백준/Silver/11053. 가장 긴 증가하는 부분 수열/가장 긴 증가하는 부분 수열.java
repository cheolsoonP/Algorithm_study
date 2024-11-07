import java.util.*;
import java.io.*; 

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		/*
		 * DP[1] = 1; 
		 * for i=0~N-1  
		 * 		for j=0~i-1 
		 * 			if (arr[j] < arr[i]) 
		 * 				DP[i] = Math.max(DP[i], DP[j]+1); 
		 * 
		 * DP[i] = 
		 * */
		
		int N = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		int[] A = new int[N];
		int[] DP = new int[N];
		for (int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
			DP[i] = 1;
		}
		DP[0] = 1; 
		int result = DP[0];
		for (int i=1;i<N;i++) {
			for (int j=0;j<i;j++) {
				if (A[j] < A[i]) {
					DP[i] = Math.max(DP[i], DP[j]+1);
					result = Math.max(DP[i], result);
				}
			}
		}
		
		System.out.println(result);
	}

}
