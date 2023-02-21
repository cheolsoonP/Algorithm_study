import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BOJ_11286_절댓값힙 {
	
	static int n, arr[], oper, ans;
	static PriorityQueue<Integer> minusQ = new PriorityQueue<>();
	static PriorityQueue<Integer> plusQ = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < n; i++) {
			oper = Integer.parseInt(in.readLine());
			
			if (oper == 0) { // 값 출력
				if (minusQ.isEmpty() && plusQ.isEmpty())// 배열이 비어있다면 0 출력
					ans = 0;
				else if (minusQ.isEmpty()) // minusQ가 비어있다면 
					ans = plusQ.poll();
				else if (plusQ.isEmpty()) // plusQ가 비어있다면
					ans = -minusQ.poll();
				else { // +, - 배열 둘 다 있다면 
					if(plusQ.peek() >= minusQ.peek()) // 값이 작은쪽이 이긴다. 값이 같으면 minus가 이긴다.
						ans = -minusQ.poll();
					else 
						ans = plusQ.poll();
				}
				sb.append(ans).append("\n");
			}else if (oper < 0) { // 값 추가
				minusQ.offer(Math.abs(oper));
			}else if (oper > 0) { // 값 추가
				plusQ.offer(oper);
			}
		}
		
		System.out.println(sb);
	}
}
