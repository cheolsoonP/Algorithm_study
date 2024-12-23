import java.util.*;
import java.io.*;
 
public class Main {
	static List<Integer> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		
		list = new ArrayList<>(); 
		
		// 1. 1 ~ 4000000 까지 소수 찾기  
		int target = Integer.parseInt(in.readLine());
		eratosthenes(target);
		
		// 2. 연속된 합으로 나타낼 수 있는지 찾기 
		int left = 0; 
		int right = 0; 
		int cnt = 0; 
		int sum = 0; 
		while(left<list.size()) {
			if (sum == target) cnt++;

			if (sum < target && right < list.size()) {
				sum += list.get(right++);
			} else {
				sum -= list.get(left++);
			}			
		}

		System.out.println(cnt);
		
	}
	
	private static void eratosthenes(int n) {
		boolean[] isPrime = new boolean[n+1];
		
		// 모든 수 소수로 초기화 
		for (int i=2;i<=n;i++) {
			isPrime[i] = true;
		}
		
		// 에라토스테네스의 체 알고리즘 적용 
		for (int i=2;i*i<=n;i++) {
			if(isPrime[i]) {
				for (int j=i*i;j<=n;j+=i) {
					isPrime[j] = false; 
				}
			}
		}
		
		// 소수 출력 
		for (int i=2;i<=n;i++) {
			if(isPrime[i]) {
				list.add(i);
			}
		}
	}
	
}
