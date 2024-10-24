import java.util.*;
import java.io.*; 


public class Main {
	static int N,K; 
	static int[] ranK;
	static long maxRan;
	public static void main(String[] args) throws Exception {
		/*
		 * N개 랜선 만들자 
		 * 이미 영식 -> K개 랜선 -> 길이 다름 
		 * 성원 -> N개 같은 길이 랜선 원함 
		 * N개보다 많이 만드는 것도 N개를 만드는 것에 포함됨 
		 * 만들 수 있는 최대 랜선의 길이를 구해라 
		 * 
		 * 특정 길이를 만든다고 했을 때 -> 가능한가? 
		 * 길이 x를 N 개 만들 수 있는가? 
		 * 랜선의 길이가 X일 때 랜선이 N개 이상인가? 
		 * 
		 * 랜선 길이가 mid -> N개 미만 -> end = mid-1 
		 * 랜선 길이가 mid -> N개 이상 -> start = mid 
		 * 
		 * */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		ranK = new int[K];
		for (int i=0;i<K;i++) {
			ranK[i] = Integer.parseInt(in.readLine());
		}
		
		find();
		
		System.out.println(maxRan);
		
	}

	private static void find() {
		long start = 1;
		long end = Integer.MAX_VALUE;
		
		while (start < end) {
			long mid = (start+end+1) / 2;
			//System.out.println(mid);
			// check possible 
			int cnt = 0;
			for (int i=0;i<K;i++) {
				cnt += ranK[i]/mid;
			}
			if (cnt >= N) {
				start = mid;
			} else {
				end = mid - 1;
			}
		}
		
		maxRan = start;
	}
}
