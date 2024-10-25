import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int K; 
	static Map<Integer, Integer> map;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder(); 
		T = Integer.parseInt(in.readLine());
		for (int tc=1;tc<=T;tc++) {
			K = Integer.parseInt(in.readLine());
			map = new TreeMap<>();
			
			for (int i=0;i<K;i++) {
				st = new StringTokenizer(in.readLine());
				
				char cmd = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				//System.out.println("cmd: " + cmd+", num: "+num);
				//System.out.println("before\t: "+Arrays.toString(set.toArray()));
				
				if (cmd == 'I') {
					if (map.containsKey(num)) {
						map.put(num, map.get(num)+1);
					} else {
						map.put(num, 1);
					}
				} else if (cmd == 'D' && num == 1) {
					// 최댓값 삭제 
					if (map.isEmpty()) continue;
					
					int max = ((TreeMap<Integer, Integer>) map).lastKey();
					if (map.get(max) == 1) {
						map.remove(max);
					} else {
						map.put(max, map.get(max)-1);
					}
				} else if (cmd == 'D' && num == -1) {
					// 최솟값 삭제 
					if (map.isEmpty()) continue;
					
					int min = ((TreeMap<Integer, Integer>) map).firstKey();
					if (map.get(min) == 1) {
						map.remove(min);
					} else {
						map.put(min, map.get(min)-1);
					}
				}				
				//System.out.println("after\t: "+Arrays.toString(set.toArray()));
			}
			
			if (map.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				Integer min = ((TreeMap<Integer, Integer>) map).firstKey();
				Integer max = ((TreeMap<Integer, Integer>) map).lastKey();
				sb.append(max).append(" ").append(min).append("\n");
			}			
		}
		System.out.println(sb.toString());
		
	}
}
