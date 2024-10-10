import java.util.*;
import java.io.*; 

public class Main {
	static int MAX_CNT = 10000;
	static int[] ARR = new int[MAX_CNT];
	static int N; 
	static int pos = 0; 
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Arrays.fill(ARR, -1);
		N = Integer.parseInt(in.readLine());
		StringTokenizer st = null;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			String cmd = st.nextToken();
			if (cmd.equals("push")) {
				push(Integer.parseInt(st.nextToken()));
			} else if (cmd.equals("pop")) {
				pop();
			} else if (cmd.equals("size")) {
				size();
			} else if (cmd.equals("empty")) {
				empty();
			} else if (cmd.equals("top")) {
				top();
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static void push(int num) {
		ARR[pos] = num;
		//sb.append(ARR[pos]+"\n");
		pos++;
	}
	
	private static void pop() {
		if (pos == 0) {
			if (ARR[pos] == -1) {
				sb.append("-1\n");							
			}
		} else {
			sb.append(ARR[pos-1]+"\n");
			ARR[pos-1] = -1;
			pos--;
		}
	}
	
	private static void size() {
		if (pos == 0) {
			if (ARR[pos] == -1) {
				sb.append("0\n");
			} else {
				sb.append("1\n");
			}
		} else {
			sb.append((pos)+"\n");
		}
	}
	
	private static void empty() {
		if (pos == 0 && ARR[pos] == -1) {
			sb.append("1\n");
		} else {
			sb.append("0\n");			
		}
	}
	
	private static void top() {
		if (pos == 0 && ARR[pos] == -1) {
			sb.append("-1\n");			
		} else {
			sb.append(ARR[pos-1]+"\n");
		}
	}
}
