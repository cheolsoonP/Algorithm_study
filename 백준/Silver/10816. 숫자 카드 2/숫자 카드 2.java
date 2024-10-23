import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		Map<Integer, Integer> map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			if (map.containsKey(num)) {
				map.put(num, map.get(num)+1);
			} else {
				map.put(num, 1);
			}
		}
		int M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder(); 
		for (int i=0;i<M;i++) {
			int num = Integer.parseInt(st.nextToken());
			if (map.containsKey(num)) {
				sb.append(map.get(num)).append(" ");
			} else {
				sb.append(0).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
}
