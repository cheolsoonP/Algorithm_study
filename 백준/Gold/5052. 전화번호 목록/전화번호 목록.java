import java.io.*;
import java.util.*;

public class Main {
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(in.readLine());
			String num[] = new String[N];
			Map<String, Integer> map = new HashMap<>();
			
			for(int i=0;i<N;i++) {
				num[i] = in.readLine();
				map.put(num[i], 1);
			}
			
			// Arrays.sort(num);
			if(isConsistent(num, map)) {
				sb.append("YES\n");
			}else {
				sb.append("NO\n");
			}
		}
		
		System.out.println(sb);
		in.close();
	}

	private static boolean isConsistent(String[] num, Map<String, Integer> map) {
		for(int i=0;i<N;i++) {
			for(int j=1;j<num[i].length();j++) {
				if(map.containsKey(num[i].substring(0, j))) {
					return false;
				}
			}
		}
		return true;
	}
}
