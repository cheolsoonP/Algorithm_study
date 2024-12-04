import java.util.*;
import java.io.*;
 
public class Main {

	static int N; 
	static int[] arr; 
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(in.readLine()); 
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int result = Integer.MAX_VALUE; 
		for (int i=0;i<N-1;i++) {
			int left = i+1;
			int right = N-1;
			
			while(left <= right) {
				int mid = (right+left)/2;
				int sum = arr[i] + arr[mid];
				if (Math.abs(sum) < Math.abs(result)) {
					result = sum; 
				}
				if (sum < 0) {
					left = mid+1; 
				} else {
					right = mid-1; 
				}
			}
			
		}
		System.out.println(result);
		
	}

}
