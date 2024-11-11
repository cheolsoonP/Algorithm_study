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
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		
		
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<M;i++) {
			int target = Integer.parseInt(st.nextToken());
			int isFind = find(target);
			sb.append(isFind+" ");
		}

		System.out.println(sb.toString());
	}
	
	private static int find(int target) {
	
		int start = 0; // start index 
		int end = N-1; // end index 
		
		while(start <= end) {
			int mid = (start+end+1)/2; 
			if (arr[mid] == target) {
				return 1; 
			}
			
			if (arr[mid] < target) {
				start = mid+1;
			} else if (arr[mid] > target) {
				end = mid-1; 
			}

		}
		
		return 0; 
		
	}

}
