import java.util.*;
import java.io.*;

public class Main {
	static int r,c; // 반시계로 돌린다고 생각
	static int graph[][];
	// 하 우 상 좌
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		c = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		graph = new int[r][c];
		int target = Integer.parseInt(in.readLine());
		if(target > r*c) {
			System.out.println(0);
			return;
		}
		int x=0, y=0;
		int dir = 0;
		int curr = 1;
		int nx, ny;
		while(true) {
			if(curr == target) {
				System.out.println((y+1)+" "+(x+1));
				return;
			}
			
			graph[x][y] = curr++;
			nx = x+dx[dir];
			ny = y+dy[dir];
			if(nx<0 || nx>=r || ny<0 || ny>=c || graph[nx][ny] > 0) {
				dir = (dir+1)%4;
			}
			x+=dx[dir];
			y+=dy[dir];
		}

	}
	
}
