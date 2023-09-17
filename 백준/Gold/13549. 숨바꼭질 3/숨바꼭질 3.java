import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int result; 
	static int cost[]; // cost 저장 
	static int MAX = 100000;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		cost = new int[1000001];
		Arrays.fill(cost, -1);
		
		bfs();
	}
	private static void bfs() {
		
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(N);
		cost[N] = 0;
		
		while(!q.isEmpty()) {
			int curr = q.poll(); // 현재 위치
			// 범위를 넘지 않으면서 아직 방문하지 않은 곳이면
			
			if(curr == K) break; // + 종료조건 추가(목적지인 경우)

			if(curr * 2 <= MAX && cost[curr*2] == -1) {
				q.offer(curr*2);
				cost[curr*2] = cost[curr]; // 비용 그대로
			}
			if(curr-1 >= 0 && cost[curr-1] == -1) {
				q.offer(curr-1);
				cost[curr-1] = cost[curr]+1; // 비용 1증가 
			}
			if(curr+1 <= MAX && cost[curr+1] == -1) {
				q.offer(curr+1);
				cost[curr+1] = cost[curr]+1; // 비용 1증가 
			}
		}
		
		System.out.println(cost[K]);
	}

}
