import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static String[] pocketMonNum;
	static Map<String, Integer> pocketMonName;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		pocketMonNum = new String[100001];
		pocketMonName = new HashMap<>(); 
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i=1;i<=N;i++) {
			String name = in.readLine();
			pocketMonNum[i] = name;
			pocketMonName.put(name, i);
		}
		
		for (int i=1;i<=M;i++) {
			String q = in.readLine();
			if (q.charAt(0) >= '1' && q.charAt(0) <= '9') {
				int num = Integer.parseInt(q);
				System.out.println(pocketMonNum[num]);
			} else {
				System.out.println(pocketMonName.get(q));
			}
			
		}
		
	}
}
