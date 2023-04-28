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
			
			for(int i=0;i<N;i++) {
				num[i] = in.readLine();
			}
			
			Arrays.sort(num);
			if(isConsistent(num)) {
				sb.append("YES\n");
			}else {
				sb.append("NO\n");
			}
		}
		
		System.out.println(sb);
		in.close();
	}

	private static boolean isConsistent(String[] num) {
		for(int i=0;i<N-1;i++) {
			if(num[i+1].startsWith(num[i])) {
				return false;
			}
		}
		return true;
	}
}
