import java.util.*;
import java.io.*;
 
public class Main {
	static int K; 
	static int[] arr; 
	static Stack<Integer> stack; 
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		
		stack = new Stack<>();
		K = Integer.parseInt(in.readLine());
		for (int i=0;i<K;i++) {
			int num = Integer.parseInt(in.readLine());
			if (num == 0 && !stack.empty()) {
				stack.pop();
			} else {
				stack.push(num);
			}
		}
		
		int sum = 0;
		for (int num : stack) {
			sum += num;
		}
		System.out.println(sum);
		
	}

}
