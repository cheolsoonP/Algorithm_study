import java.util.*;
import java.io.*;

public class Main {
	static int N,M; 
	static int[] arr; 

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());		
		arr = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}
		Arrays.sort(arr);
		//System.out.println(Arrays.toString(arr));
				
		M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<M;i++) {
			int num = Integer.parseInt(st.nextToken());
			if(find(num)) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

	private static boolean find(int target) {
		int start = 0;
		int end = N-1;
		while (start <= end) {
			int mid = (start+end) / 2;
			if (arr[mid] < target) {
				start = mid+1;
			} else if (arr[mid] > target) {
				end = mid-1;
			} else {
				return true;
			}
		}
		return false;
	}
}
