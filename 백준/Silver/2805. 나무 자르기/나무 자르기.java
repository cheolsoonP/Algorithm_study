import java.io.*;
import java.util.*;

public class Main {
	static int N; 
	static long M; 
	static long[] arr; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
	
		/*
		 * M미터 필요 
		 * 높이 H 지정 
		 * 나무를 필요한 만큼만 들고 갈거다 
		 * 적어도 M미터 나무를 가져가기 위한 절단기의 높이의 최대값을 구해라 
		 * */
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		arr = new long[N];
		
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);
		long left = 0; 
		long right = arr[N-1];
		
		while(left < right) {
			long mid = (left+right)/2;
			
			long curr = 0; 
			for (int i=0;i<N;i++) {
				if (arr[i] - mid > 0) {						
					curr += arr[i] - mid;
				}
			}
			
			if (curr >= M) {
				left = mid+1;
			} else {
				right = mid; 
			}
		}
		System.out.println(left-1);

	}
}
