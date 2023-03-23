import java.io.*;
import java.util.*;

public class Main {
	private static void check(char[] arr) {
		Deque<Character> st = new ArrayDeque<>();
		for (int i = 0; i < arr.length; i++) {
			if(!st.isEmpty()) {
				if(st.peek() == '(' && arr[i] == ')') {
					st.pop();
				}else {
					st.offer(arr[i]);
				}				
			}else {
				st.offer(arr[i]);
			}
		}
		if(!st.isEmpty()) System.out.println("NO");
		else System.out.println("YES");
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(in.readLine());
		for (int i = 0; i < t; i++) {
			char[] arr = in.readLine().toCharArray();
			check(arr);
		}
	}

}
