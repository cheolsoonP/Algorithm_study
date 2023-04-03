import java.util.*;
import java.io.*;


public class Main {
	static int n, graph[][], startx, starty, endx, endy, node[][];
	static boolean isPossible, visited[];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		for(int testCase=1;testCase<=T;testCase++) {
			n = Integer.parseInt(in.readLine());
			graph = new int[n+2][n+2];
			node = new int[n+2][2];
			visited = new boolean[n+2];
			st = new StringTokenizer(in.readLine());
			node[0][0] = Integer.parseInt(st.nextToken());
			node[0][1] = Integer.parseInt(st.nextToken());
			for(int i=1;i<=n;i++) {
				st = new StringTokenizer(in.readLine());
				node[i][0]= Integer.parseInt(st.nextToken());
				node[i][1]= Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			node[n+1][0] = Integer.parseInt(st.nextToken());
			node[n+1][1] = Integer.parseInt(st.nextToken());

			for(int i=0;i<n+2;i++) {
				for(int j=0;j<n+2;j++) {
					int dist = Math.abs(node[i][0]-node[j][0])+Math.abs(node[i][1]-node[j][1]);
					graph[i][j] = dist;
					graph[j][i] = dist;
				}
			}

			// 한번에 최대 이동거리 1000
			ArrayDeque<Integer> q = new ArrayDeque<>();
			q.offer(0);
			isPossible = false;
			visited[0] = true;
			while(!q.isEmpty()) {
				int curr = q.pollFirst();
				if(curr == n+1) {
					isPossible = true;
					System.out.println("happy");
					break;
				}
				for(int i=0;i<n+2;i++) {
					if(visited[i]) continue;
					if(graph[curr][i] != 0 && graph[curr][i] <= 1000) {
						visited[i] = true;
						q.offer(i);
					}
				}
			}
			if(!isPossible) System.out.println("sad");
		}
		
		
	}

}
