import java.io.*;
import java.util.*; 

/*
 * 정수 N -> 연산 세개 적절히 써서 1만들어라 
 * arr[a] = 1 (a의 최소) 
 * X에 대해서 
 * [0,1,2] 
 * [0,1,2]
 * []
 * 
 * D[i] = i를 1로 
 * D[12] = ? 
 * 3으로 나누거나 D[12] = D[4] + 1
 * 2로 나누거나 D[12] = D[6] + 1 
 * 1을 빼거나 D[12] = D[11] + 1
 * 나는 D[1] ~ D[11] 까지 값을 알고있다. 
 * 
 * D[k] = ? 
 * D[k] = D[k/3] + 1
 * D[k] = D[k/2] + 1
 * D[k] = D[k-1] + 1
 * 이들 중 최솟값 
 * 초기값 정의하기 
 * D[1] = 0 
 * */
public class Main {
	static int[] D;
	static int N; 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		D = new int[N+1];
		D[1] = 0; 
		
		for (int i=2;i<=N;i++) {
			D[i] = D[i-1]+1;
			if (i%3==0) {
				D[i] = Math.min(D[i], D[i/3]+1);
			}
			if (i%2==0) {
				D[i] = Math.min(D[i], D[i/2]+1);
			}
		}
		
		System.out.print(D[N]);
		
	}
	
}
