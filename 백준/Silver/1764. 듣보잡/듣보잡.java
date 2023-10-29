
import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	// 듣보잡 = 듣지 못한 + 보지 못한
	static int result;
	static HashSet<String> set;
	static ArrayList<String> duplicationList;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		set = new HashSet<>();
		duplicationList = new ArrayList<>();
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		result = 0;
		for(int i=0;i<N;i++) {
			set.add(in.readLine());
		}
		for(int i=0;i<M;i++) {
			String curr = in.readLine();
			if(set.contains(curr)) {
				result++;
				duplicationList.add(curr);
//				System.out.println("중복");
			}
		}
		
		Collections.sort(duplicationList);
		
		System.out.println(result);
		for(String temp : duplicationList) {
			System.out.println(temp);
		}
		
		
	}
}
