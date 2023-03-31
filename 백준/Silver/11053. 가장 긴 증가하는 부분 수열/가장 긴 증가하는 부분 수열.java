import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		int n = Integer.parseInt(in.readLine());
		int arr[] = new int[n];
		int dp[] = new int[n];
		
		Arrays.fill(dp,1);
		st = new StringTokenizer(in.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<=i;j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		
		int result = 0;
		for(int i=0;i<n;i++) {
			result = Math.max(result, dp[i]);
		}
		System.out.println(result);
		
	}
}
