import java.util.*;
import java.io.*; 

public class Main {
	static int N,K; 
	static int[] ranK;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		ranK = new int[K];
		for (int i=0;i<K;i++) {
			ranK[i] = Integer.parseInt(in.readLine());
		}
		
		System.out.println(find());
		
	}

	private static long find() {
		long start = 1;
		long end = Integer.MAX_VALUE;
		
		while (start < end) {
			long mid = (start+end+1) / 2;
			if (solve(mid)) {				
				start = mid;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}
	
	private static boolean solve(long mid) {
		int cnt = 0;
		for (int i=0;i<K;i++) {
			cnt += ranK[i]/mid;
		}
		if (cnt >= N) {
			return true;
		}
		return false;
	}
}
