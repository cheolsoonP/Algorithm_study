import java.io.*;
import java.util.*;

public class Main {
	static class Box {
		int x;
		int y;
		int size;
		String str;
		
		public Box(int x, int y, int size, String str) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.str = str;
		}
	}
	static int N,M,K;
	static char[][] graph;
	static int maxLen;
	static HashMap<String, Integer> god;
	// 상 우상 우 우하 하 좌하 좌 좌상
	static int dx[] = {-1,-1,0,1,1,1,0,-1};
	static int dy[] = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new char[N][M];
		for(int i=0;i<N;i++) {
			graph[i] = in.readLine().toCharArray();
		}
		god = new HashMap<>();
		for(int i=0;i<K;i++) {
			String temp = in.readLine();
			maxLen = Math.max(maxLen, temp.length());
			god.put(temp, 0);
		}
		
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				bfs(i, j);
			}
		}
		
		for(int res: god.values()) {
			sb.append(res+"\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int r, int c) {
		Deque<Box> q = new ArrayDeque<>();
		q.offer(new Box(r,c,1,String.valueOf(graph[r][c])));
		
		while(!q.isEmpty()) {
			Box temp = q.pollFirst();
			
			if(god.containsKey(temp.str)) {
				int value = god.get(temp.str);
				god.replace(temp.str, value+1);
				continue;
			}
			if(temp.size >= maxLen) continue;
			
			for(int i=0;i<8;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				
				if(nx < 0) nx = N-1;
				if(ny < 0) ny = M-1;
				if(nx >= N) nx = 0;
				if(ny >= M) ny = 0;
				
				q.offer(new Box(nx, ny, temp.size+1, temp.str+graph[nx][ny]));
			}
		}
	}

}
