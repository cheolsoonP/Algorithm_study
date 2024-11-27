import java.io.*;
import java.util.*;

public class Main {

	static int dx[] = {-1,-2,-2,-1,1,2,2,1};
	static int dy[] = {-2,-1,1,2,2,1,-1,-2};
	static int N; 
	static int[] curr; 
	static int[] target; 
	static int[][] visited; 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		curr = new int[2];
		target = new int[2]; 
		
		int T = Integer.parseInt(in.readLine());
		for (int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine());
			curr[0] = Integer.parseInt(st.nextToken());
			curr[1] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(in.readLine());
			target[0] = Integer.parseInt(st.nextToken());
			target[1] = Integer.parseInt(st.nextToken());

			visited = new int[N][N];
			for (int i=0;i<N;i++) {
				Arrays.fill(visited[i], -1);
			}
			visited[curr[0]][curr[1]] = 0; 
			
			Deque<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] {curr[0], curr[1]});
			while(!queue.isEmpty()) {
				int[] pos = queue.poll();
				for (int dir=0;dir<8;dir++) {
					int nx = pos[0] + dx[dir];
					int ny = pos[1] + dy[dir];
					if (nx<0||nx>=N||ny<0||ny>=N) continue;
					if (visited[nx][ny] >= 0) continue; 
					visited[nx][ny] = visited[pos[0]][pos[1]] + 1;
					queue.add(new int[] {nx,ny});
					if (nx == target[0] && ny == target[1]) break;
				}
			}
			
			System.out.println(visited[target[0]][target[1]]);
		}
		
	}

}
