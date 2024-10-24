import java.util.*;
import java.io.*;

public class Main {
	static int N; 
	static int S; 
	static int[] arr; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for (int i=0; i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		
		int total = arr[0];
		int right = 0;
		int length = Integer.MAX_VALUE;		
		for (int left=0;left<N;left++) {
			while(right<N && total < S) {
				right++;
				if(right != N) total += arr[right];
			}
			if(right == N) break; 
			length = Math.min(length, right-left+1);
			total -= arr[left];
		}
		
		if (length == Integer.MAX_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(length);
		}		
	}
}
