import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int n = sc.nextInt();
		int target = sc.nextInt();
		
		int x = n/2;
		int y = n/2;
		int graph[][] = new int[n][n];
		
		int dx[] = new int[] {-1,0,1,0};
		int dy[] = new int[] {0,1,0,-1};
		int tx = 0;
		int ty = 0;
		int cnt = 0;
		int round = 1;
		int nx = x;
		int ny = x;
		int dir = 0;
		int idx = 1;
		graph[x][y] = 1;
		while(true) {
			// 2번씩
			if(cnt == 2) {
				cnt = 0;
				round++;
			}
			// round만큼
			for(int i=0;i<round;i++) {
				nx += dx[dir];
				ny += dy[dir];
				graph[nx][ny] = ++idx;
				if(nx == 0&&ny == 0) break;
			}
			
			if(nx == 0&&ny == 0) break;
			cnt++;
			dir = (dir+1)%4;
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(graph[i][j] == target) {
					tx = i;
					ty = j;
				}
				sb.append(graph[i][j]+" ");
			}
			sb.append('\n');			
		}
		
		sb.append((tx+1)+" "+(ty+1));
		
		System.out.println(sb);
	}
}
