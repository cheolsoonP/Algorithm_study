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
	
		/*
		 * 과자의 길이 배열에서 가장 긴 Max를 찾는다 
		 * 문제 과자 길이 최소는 1 -> left 최대값 -> right 
		 * mid -> 각 길이에서 mid로 나눈 몫을 다 더한다. 
		 * 다 더한 결과가 원하는 개수보다 많다면 -> left = mid+1 
		 * 원하는 개수보다 적다면 -> right = mid-1 
		 * left <= right 동안 반복한다. 
		 * 
		 * */
		
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
