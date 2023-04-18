import java.util.*;
import java.io.*;

public class Main {
	/*
	 * 같은 눈 3개 - 10000원 + 같은눈 * 1000
	 * 같은눈 2개 - 1000원 + 같은눈 * 100
	 * 모두 다른 눈  - 가장 큰눈 * 100
	 * 
	 * */
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int result = 0;
		st = new StringTokenizer(in.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		if((A == B) && (B == C)) {
			result = 10000+(A*1000);
		}else if(A == B) {
			result = 1000+(A*100);
		}else if(A == C) {
			result = 1000+(A*100);
		}else if(B == C) {
			result = 1000+(B*100);
		}else {
			result = Math.max(C, Math.max(A, B))*100;
		}
		System.out.println(result);
	}
}
