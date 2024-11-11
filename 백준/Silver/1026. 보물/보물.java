import java.io.*;
import java.util.*;
public class Main {
	static int N; 
	static int[] A; 
	static Integer[] B; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		N = Integer.parseInt(in.readLine());
		A = new int[N];
		B = new Integer[N]; 
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A); // 최소값 순서 
		Arrays.sort(B, Collections.reverseOrder()); // 최대값 순서 
		int sum = 0; 
		for (int i=0;i<N;i++) {
			sum += (B[i] * A[i]);
		}
		System.out.println(sum);
	}

}
