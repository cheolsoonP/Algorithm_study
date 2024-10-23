import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] arr;
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		 
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		
		M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<M;i++) {
			int num = Integer.parseInt(st.nextToken()); 
			int lowerIdx = findLowerIdx(num);
			int upperIdx = findUpperIdx(num);
			sb.append(upperIdx - lowerIdx).append(" ");
		}
		System.out.println(sb.toString());
	}



	private static int findUpperIdx(int num) {
		int start = 0; 
		int end = N;
		
		while(start<end) {
			int mid = (start+end)/2;
			if(arr[mid] > num) end = mid;
			else start = mid+1;
		}
		return start;
	}


	private static int findLowerIdx(int num) {
		int start = 0; 
		int end = N;
		
		while(start<end) {
			int mid = (start+end)/2;
			if(arr[mid] >= num) end = mid;
			else start = mid+1;
		}
		return start;
	}
}
