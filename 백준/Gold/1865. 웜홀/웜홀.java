import java.io.*;
import java.util.*;

public class Main {
	/*
	 * N개 지점
	 * M개 도로 W개 웜홀
	 * 웜홀은 방향
	 * 도로는 양방향
	 * 웜홀은 시작 -> 도착 시간 거꾸로
	 * 음의 가중치
	 * 한지점에서 출발위치로 왔을 때 시간이 되돌아 가는 경우가 있는지
	 * 음의 사이클이 있는지 궁금하다. 
	 * 가능한지 불가능한지 판단해라. 
	 * */
	static class Edge{
		int to;
		int cost;
		
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}		
	}
	static int N, M, W;
	static List<Edge> edgeList[];
	static int dist[];
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());

		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			dist = new int[N+1];
			Arrays.fill(dist, INF);
			edgeList = new ArrayList[N+1];
			for(int i=0;i<=N;i++) {
				edgeList[i] = new ArrayList<Edge>();
			}
			int start, end, cost;
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(in.readLine());
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				edgeList[start].add(new Edge(end, cost));
				edgeList[end].add(new Edge(start, cost));
			}
			
			for(int i=0;i<W;i++) {
				st = new StringTokenizer(in.readLine());
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				edgeList[start].add(new Edge(end, -cost));
			}
			boolean cycle = false;
			for(int i=1; i<=N;i++) {
				
				if(bellmanford(i)) {
					cycle = true;
					sb.append("YES\n");
					break;
				}
			}
			if(!cycle) {
				sb.append("NO\n");
			}
		}
		System.out.println(sb);
	}

	private static boolean bellmanford(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		boolean isUpdate = false;
		for(int i=1; i<=N;i++) {
			isUpdate = false;
			for(int j=1; j<=N; j++) {
				for(Edge edge: edgeList[j]) {
					if(dist[j] == INF) continue;
					
					if(dist[edge.to] > dist[j] + edge.cost) {
						dist[edge.to] = dist[j] + edge.cost;
						isUpdate = true;
					}
				}
			}
			if(!isUpdate) {
				break;
			}
		}
		
		if(isUpdate) {
			for(int i=1; i<=N; i++) {
				for(Edge edge: edgeList[i]) {
					if(dist[i] != INF && dist[edge.to] > dist[i] + edge.cost) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
