import java.io.*;
import java.util.*;

public class Main {
	static int N; 
	static long[] arr; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		arr = new long[N];
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		long min = Long.MAX_VALUE;
		int ml =0, mr = 0;
		for(int i=0; i<N-1; i++) {
			int left =i+1;
			int right =N-1;
			while(left<=right) {
				int mid = (left+right)/2;
				long sum = Math.abs(arr[i]+arr[mid]);
				if(min > sum) {
					min = sum;
					ml = i; 
					mr = mid;
				}
				
				if(arr[mid]>= -arr[i]) {
					right = mid-1;
				}else{
					left = mid+1;
				}
			}
		}
		System.out.println(arr[ml]+" "+arr[mr]);
	}
	
}
