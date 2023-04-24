import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		
		long result = div(A, B, C);
		System.out.println(result);
	}
	private static long div(long a, long b, long c) {
		if(b == 1) {
			return a % c;
		}
		long temp = div(a, b/2, c);

		if(b % 2 == 1) {// 짝수
			return (temp * temp % c) * a % c;
		}
		return temp * temp % c;
	}
}
