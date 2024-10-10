import java.util.*;
import java.io.*;

public class Main {
	static int[] ARR = new int[10000];
	static int N;
	static int tail = 0;
	static int head = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		Arrays.fill(ARR, -1);
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
			} else if (cmd.equals("front")) {
				front();
			} else if (cmd.equals("back")) {
				back();
			}
		}
		System.out.println(sb.toString());
		
	}
	
	private static void push(int num) {
		ARR[head] = num;
		head++;
	}
	
	private static void pop() {
		if (tail == head) {
			if (ARR[tail] == -1) {
				sb.append("-1\n");				
			} else {
				sb.append(ARR[tail]+"\n");
				ARR[tail] = -1;
			}
		} else {
			sb.append(ARR[tail]+"\n");
			ARR[tail] = -1;
			tail++;
		}
	}

	private static void size() {
		if (head == tail) {
			if (ARR[head] == -1) {
				sb.append("0\n");
			} else {
				sb.append("1\n");
			}
		} else {			
			sb.append((head-tail)+"\n");
		}
	}

	private static void empty() {
		sb.append((head == tail ? 1 : 0)+"\n");
	}
	
	private static void back() {
		if (tail == head) {
			if (ARR[head] == -1) {
				sb.append("-1\n");
			} else {
				sb.append(ARR[head]+"\n");
			}
		} else {
			sb.append(ARR[head-1]+"\n");
		}
	}
	
	private static void front() {
		if (tail == head) {
			if (ARR[tail] == -1) {
				sb.append("-1\n");
			} else {
				sb.append(ARR[tail]+"\n");
			}
		} else {
			sb.append(ARR[tail]+"\n");
		}
		
	}

}
