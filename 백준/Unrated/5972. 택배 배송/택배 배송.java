import java.io.*;
import java.util.*;
/*
 * 농부 현서 -> 배달
 * 가는길에 만나는 모든 소들에게 맛있는 여물을 줘야 한다. 
 * 최소한의 소를 만나고 싶다
 * 지도 50000개 헛간, 길 M 50000 양방향 길
 * 각 길에 C마리 소가 있다. 
 * 소들은 A 와 B를 잇는다
 * 두개 헛간 -> 하나 이상 길로 연결되어 있을 수 있다. 
 * 현서 - 1 찬홍 - N
 * 
 *  
 * 
 * */

public class Main {
	static class Node implements Comparable<Node> {
		int index;
		int cost;
		
		public Node(int index, int cost) {
			super();

			this.index = index;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Link [index=" + index + ", cost=" + cost + "]";
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;			
		}
	}
	static int N,M;
	static boolean check[];
	static int dist[];
	static List<Node> graph[];
	static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N+1];
		check = new boolean[N+1];
		graph = new ArrayList[N+1];
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<Node>();
		}
		int from, to, cost;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, cost));
			graph[to].add(new Node(from, cost));
		}
		
		Arrays.fill(dist, INF);
		dist[1] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1,0));
		while(!pq.isEmpty()){
			int nowVertex = pq.poll().index;
			
			// 방문하지 않은 노드라면 인접 노드 확인			
			if(check[nowVertex]) continue;
			check[nowVertex] = true;
			
			for(Node next: graph[nowVertex]) {
				if(dist[next.index] > dist[nowVertex] + next.cost) {
					dist[next.index] = dist[nowVertex] + next.cost;
					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}
		
        //최소거리 출력
//		int result = 0;
//		if(dist[N] == INF) System.out.println();
		System.out.println(dist[N]);
	}

}
