import java.util.*;
import java.io.*;

public class Main {

	static int T, n, graph[][], dp[][], result;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while(true) {
			cnt++;
			n = Integer.parseInt(in.readLine());
			if(n == 0) break;
            
			graph = new int[n][n];
			dp = new int[n][n];
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
                return o1[0]-o2[0];
            });

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0;j<n;j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}			
			for(int i=0;i<n;i++)
				Arrays.fill(dp[i], Integer.MAX_VALUE);

			pq.add(new int[] {graph[0][0], 0,0});
			dp[0][0] = graph[0][0];
			
			while(!pq.isEmpty()) {
				int temp[] = pq.peek();
				int dist = temp[0];
				int x = temp[1], y = temp[2];
				pq.poll();
				if(dp[x][y] < dist) continue;
				for (int i = 0; i < 4; i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					if(nx<0||nx>=n||ny<0||ny>=n) continue;
					if(dp[nx][ny]<=dist+graph[nx][ny]) continue;
					dp[nx][ny] = dist+graph[nx][ny];
					pq.add(new int[] {dp[nx][ny], nx,ny});
				}
			}
			sb.append("Problem "+cnt+": ").append(dp[n-1][n-1]).append('\n');
		}
		System.out.println(sb);
	}

}
