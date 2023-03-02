import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 노드가 최대 20000개 -> 인접 행렬 사용하면 메모리 초과
 * 인접 리스트 -> 메모리 효율성 증가!!
 * */

public class Main_BOJ_1753_최단경로 {
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
		
		int min=0, curr=0;
		for(int i=1; i<=V; i++) {
			curr = -1;
			min = INF;
			
			// a단계 : 방문하지 않은 정점들 중 최소 가중치의 정점 선택
			for (int j = 1; j <= V; j++) {
				System.out.println(distance[j]);
				if(!visited[j] && distance[j] < min) {
					min = distance[j];
					curr = j;
				}
			}
			if(curr == -1) break; // 더이상 방문할 노드가 없으면 종료
			visited[curr] = true; // 선택 정점 방문 처리
			
			// curr을 정점으로 경유하여 갈 수 있는 다른 방문하지 않은 정점들에 대해 처리
			for (Vertex e: nodeList[curr]) { 
				if(!visited[e.v] && distance[e.v] > min + e.w) {
					// min - 지금까지 최소 길이, e.w - 현재부터 다음 노드의 길이
					distance[e.v] = min + e.w;
				}
			}
		}
		
		
		for (int i = 1; i <= V; i++) {
			if(distance[i] == INF) {
				System.out.println("INF");
			}else {				
				System.out.println(distance[i]);
			}
		}
	}

}
