import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M; 
	static int[] A; 
	static int[] B; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N];
		B = new int[M];
		
		st = new StringTokenizer(in.readLine());		
		for (int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());		
		for (int i=0;i<M;i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		Arrays.sort(B);
		List<Integer> arr = new ArrayList<>();
		for (int i=0;i<N;i++) {
			if(find(A[i])) {
				arr.add(A[i]);
			}
		}
		
		if (arr.isEmpty()) {
			sb.append(0);
		} else {
			sb.append(arr.size()+"\n");
			for (int num : arr) {
				sb.append(num+" ");
			}
		}
		System.out.println(sb.toString());
		
	}

	private static boolean find(int target) {
		int left = 0; 
		int right = M-1;
		
		while (left <= right) {
			int mid = (left+right)/2; 
			if (B[mid] == target) {
				return false; 
			}
			
			if (B[mid] < target) {
				left = mid+1;
			} else if (B[mid] > target) {
				right = mid-1;
			}
		}
		
		return true;
	}
	

}
