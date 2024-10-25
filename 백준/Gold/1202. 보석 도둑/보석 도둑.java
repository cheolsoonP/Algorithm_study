import java.io.*; 
import java.util.*;

public class Main {
	static int N, K; 
	static Map<Integer, Integer> bag; 
	static int[][] item; // 가격, 무게 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bag = new TreeMap<>();		
		item = new int[N][2];
		
		for (int i=0; i<N;i++) {
			st = new StringTokenizer(in.readLine());
			// 무게, 가격 
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			item[i][0] = M; 
			item[i][1] = V;
		}
		// 보석 가격 순으로 정렬 
		Arrays.sort(item, (a,b) -> Integer.compare(a[1], b[1]));

		// 가방 
		for (int i=0;i<K;i++) {
			int W = Integer.parseInt(in.readLine());
			if (bag.containsKey(W)) {
				bag.put(W, bag.get(W)+1);
			} else {
				bag.put(W, 1);
			}
		}

		long total = 0;
		for (int i=N-1;i>=0;i--) {
			int weight = item[i][0];
			int value = item[i][1];
			Integer currBag = ((TreeMap<Integer,Integer>)bag).ceilingKey(weight);
			//System.out.println("weight: "+weight+", currBag: "+currBag);			
			if (currBag != null) {
				if (bag.get(currBag) == 1) {					
					bag.remove(currBag);
				} else {
					bag.put(currBag, bag.get(currBag)-1);
				}
				total += value;
			}
		}
		System.out.println(total);
	}

}
