import java.io.*;
import java.util.*;

public class Main {

	static int N; 
	static int M; 
	static int[] arr; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
	
		st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		arr = new int[N];
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); 
		
		int result = 0; 
		int left = 1; 
		int right = arr[N-1];
		
		while(left <= right) {
			int mid = (left+right)/2;
			int cnt = 0;
			for (int i=0;i<N;i++) {
				cnt += (arr[i] / mid);
				if (cnt >= M) break; // 빠르게
			}
			if (cnt >= M) {
				left = mid+1;
				result = mid;
			} else {
				right = mid-1;
			}
		}
		
		System.out.println(result);
		
	}
}
