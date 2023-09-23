
import java.io.*;
import java.util.*;

public class Main {
	static class Group implements Comparable<Group> {
		int cnt;
		Group(int cnt) {
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Group o) {
			return this.cnt - o.cnt;
		}
	}

	static int N;
	static int map[][];
	static boolean visit[][];
	static int group;
	static PriorityQueue<Group> numOfGroup = new PriorityQueue<>();
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i=0;i<N;i++) {
			String tmp = in.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}
		group = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == 1 && visit[i][j] == false) {
					bfs(i, j);
					group++;
				}
			}
		}
		System.out.println(group);
		for(int i=0;i<group;i++) {
			System.out.println(numOfGroup.poll().cnt);			
		}
		
	}
	private static void bfs(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x,y});
		visit[x][y] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			int [] temp = q.pollFirst();
			x = temp[0];
			y = temp[1];
			
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if(map[nx][ny] == 1 && visit[nx][ny] == false) {
					q.offer(new int[] {nx,ny});
					visit[nx][ny] = true;
					cnt++;
				}
			}
		}
		numOfGroup.offer(new Group(cnt));
	}
}
