import java.util.*;
import java.io.*;


public class Main {
	static int N, cost[][], graph[][];
	static int minCost;
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static boolean visit[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		graph = new int[N][N];
		cost = new int[N][N];
		minCost = Integer.MAX_VALUE;
		visit = new boolean[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<N;j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(1,1,0);
		
		System.out.println(minCost);
		
	}

	private static void dfs(int row, int col, int count) {
		
		if(count == 3) {
//			printGraph();
			int currCost = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(graph[i][j] == 1) {
						currCost += cost[i][j];
					}
				}
			}
			minCost = Math.min(minCost, currCost);
			return;
		}
		
		for(int i=1;i<N-1;i++) {
			for(int j=1;j<N-1;j++) {
				if(check(i,j) && !visit[i][j]) {
					// 상하좌우 꽃이 없으면 심기
					visit[i][j] = true;
					graph[i][j] = 1;
					for(int dir=0;dir<4;dir++) {
						graph[i+dx[dir]][j+dy[dir]] = 1;
					}
					dfs(i,j,count+1);
					graph[i][j] = 0;
					for(int dir=0;dir<4;dir++) {
						graph[i+dx[dir]][j+dy[dir]] = 0;
					}
					visit[i][j] = false;
				}
				
			}
		}
	}

	private static void printGraph() {
		System.out.println("");
		for(int i=0;i<N;i++) {
			System.out.println(Arrays.toString(graph[i]));
		}
	}

	private static boolean check(int i, int j) {
		if(graph[i][j] == 1) {
			return false;
		}
		for(int dir=0;dir<4;dir++) {
			if(graph[i+dx[dir]][j+dy[dir]] == 1) {
				return false;
			}
		}
		return true;
	}

	

}
