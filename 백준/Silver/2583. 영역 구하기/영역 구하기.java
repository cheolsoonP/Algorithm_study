import java.util.*;
import java.io.*;
 
public class Main {
	static int N; 
	static int M; 
	static int K; 
	static int[][] graph; 
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
	
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		for (int i=0;i<K;i++) {
			st = new StringTokenizer(in.readLine());
			int ay = Integer.parseInt(st.nextToken());
			int ax = Integer.parseInt(st.nextToken());
			int by = Integer.parseInt(st.nextToken())-1;
			int bx = Integer.parseInt(st.nextToken())-1;
			
			for (int x=ax;x<=bx;x++) {
				for (int y=ay;y<=by;y++) {
					graph[x][y] = 1; 
				}
			}
		}
		
		visited = new boolean[N][M];
		List<Integer> list = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (graph[i][j] == 0 && !visited[i][j]) {
					list.add(visit(i,j));
				}
			}
		}
		Collections.sort(list);
		sb.append(list.size()+"\n");
		for (int item : list) {
			sb.append(item+" ");
		}
		System.out.println(sb.toString());
	}

	private static int visit(int x, int y) {
		int cnt = 0; 
		Deque<int[]> deque = new ArrayDeque<>(); 
		deque.add(new int[] {x,y});
		visited[x][y] = true; 
		cnt++;
		while(!deque.isEmpty()) {
			int[] pos = deque.poll(); 
			for (int dir=0;dir<4;dir++) {
				int nx = pos[0]+dx[dir];
				int ny = pos[1]+dy[dir];
				if (nx<0||nx>=N||ny<0||ny>=M) continue;
				if (visited[nx][ny]) continue;
				if (graph[nx][ny] == 1) continue; 
				cnt++;
				visited[nx][ny] = true;
				deque.add(new int[] {nx,ny});
			}
		}
		return cnt; 
	}
}
