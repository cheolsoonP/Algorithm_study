import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 노드가 최대 20000개 -> 인접 행렬 사용하면 메모리 초과
 * 인접 리스트 -> 메모리 효율성 증가!!
 * */

public class Main_BOJ_1753_최단경로2 {
	private static class Vertex implements Comparable<Vertex>{
		int v;
		int w;
		
		public Vertex(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.w - o.w;
		}
		@Override
		public String toString() {
			return "Node [vertec="+ v +", distance="+ w + "]";
		}
	}
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int V, E;
		List<Vertex> nodeList[];
		int distance[];
		boolean visited[];
		
		st = new StringTokenizer(in.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		nodeList = new List[V+1];
		distance = new int[V+1];
		visited = new boolean[V+1];
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);

		for (int i = 0; i <= V; i++) {
			nodeList[i] = new ArrayList<>();
		}

		int start = Integer.parseInt(in.readLine());
		int u,v,w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			nodeList[u].add(new Vertex(v, w));
		}
		
		distance[start] = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(start, 0));
		
		int min=0, curr=0;
		Vertex current = null;
		while(!pq.isEmpty()) {
			current = pq.poll();
			
			if(visited[current.v])continue;
			
			visited[current.v] = true;
			for (Vertex node:nodeList[current.v]) {
				if(!visited[node.v] && distance[node.v] > current.w+node.w) {
					distance[node.v] = current.w + node.w;
					pq.offer(new Vertex(node.v, distance[node.v]));
				}
			}
		}
	
		for (int i = 1; i <= V; i++) {
			if(distance[i] == INF) {
				sb.append("INF").append('\n');
			}else {				
				sb.append(distance[i]).append('\n');
			}
		}
		
		System.out.println(sb);
		
		in.close();
	}

}
