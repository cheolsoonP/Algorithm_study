import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main_BOJ_2164_카드2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Deque<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= n; i++) {
			q.add(i);
		}
				
		while(true) {
			if (q.size() == 1) break;
			q.pollFirst();
			q.offerLast(q.pollFirst());
		}
		
		System.out.println(q.peekFirst());		
	}
}
