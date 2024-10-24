import java.util.*;
import java.io.*;

public class Main {
	static int N,M; 
	static int[] arr; 
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(arr);
		
		int left = 0; 
		int right = 0;
		while(true) {
			//System.out.println(left+","+right);
			int dist = arr[right] - arr[left]; 
			if (dist < M) {
				if (right != N-1) right++;
				else left++;
			} else {
				result = Math.min(result, dist);
				if (left != N-1) left++;
				else right++;
			}
			if (left == N-1 && right == N-1) break;
		}
		
		System.out.println(result);
	}
}
