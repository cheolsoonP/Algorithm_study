import java.io.*;
import java.util.*;

public class Main {
	static class Bridge implements Comparable<Bridge>{
		int from;
		int to;
		int cost;
		public Bridge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Bridge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(Bridge o) {
			return this.cost - o.cost;
		}
	}

	static int N,M, graph[][], result;
	static boolean visited[][], isPossible;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static PriorityQueue<Bridge> bridgeQueue;
	static int parent[];
	static HashSet<Integer> linkedSet;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new boolean[N][M];
		bridgeQueue = new PriorityQueue<>();
		parent = new int[7];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<M;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 섬을 연결하는 최소 다리 길이의 총합
		int idx = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(graph[i][j] > 0 && !visited[i][j]) {
					bfs(i, j, ++idx); // 각 섬 id부여
				}
			}
		}

		// 간선집합을 만든다.
		// 각 섬에서 다른 모든 섬까지 최소 경로, 길이
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(graph[i][j] > 0) {
					dfs(i, j, graph[i][j]);
				}
			}
		}
		
		// 크루스칼 - 가장 비용이 적은 간선부터 선택하면서 진행
		// union find
		for(int i=0;i<=6;i++) {// parent 초기화
			parent[i] = i;
		}
		int count = 0;
		linkedSet = new HashSet<>();// 연결된 섬들 개수 확인
		while(!bridgeQueue.isEmpty()) {
			Bridge bridge = bridgeQueue.poll();
			int from = bridge.from;
			int to = bridge.to;
			int cost = bridge.cost;
			if(find(from) != find(to)) {
				union(from, to);
				count++;
				result += cost;
				
				if(count == idx-1) break;
			}
		}
		
		if(count == idx-1) System.out.println(result);
		else System.out.println("-1");
	}
	
	private static void union(int a, int b) {
		// 루트를 찾아서 부모를 합쳐준다.
		a = find(a);
		b = find(b);
		if(a != b) {
			if(a > b) parent[a] = b;
			else parent[b] = a;
		}
	}
	
	private static int find(int a) {
		if(a == parent[a]) return a;
		else return parent[a] = find(parent[a]);
	}

	private static void dfs(int x, int y, int curr) {
		// 시작 노드 -> 상 하 좌 우 방향 각각 DFS진행, 0일때만 갈 수 있다.
		int next = -1;
		for(int dir=0;dir<4;dir++) {
			int nx = x,ny=y;
			int cost = 0;
			while(true) {
				nx += dx[dir];
				ny += dy[dir];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
				if(graph[nx][ny] == curr) break;
				if(graph[nx][ny] == 0) {
					cost++;
				}
				else if(cost >= 2) {
					next = graph[nx][ny];
					// 간선 발견
					Bridge bridge = new Bridge(curr, next, cost);
					bridgeQueue.add(bridge);
					break;
				}else {
					break;
				}
			}
		}
	}

	private static void bfs(int x, int y, int idx) {
		visited[x][y] = true;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {x,y});
		while(!q.isEmpty()) {
			int temp[] = q.pollFirst();
			x=temp[0]; y=temp[1];
			graph[x][y] = idx;
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				if(visited[nx][ny]) continue;
				if(graph[nx][ny] > 0) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});					
				}
			}
		}
		
	}

}
