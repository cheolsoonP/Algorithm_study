import java.io.*;
import java.util.*;

public class Main {

	static int N, M, dir, count;
	static int currx,curry;
	// 북동남서, 상우하좌 (시계).. 나중에 반시계로 돌려야함. -1
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int graph[][];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine());
		currx = Integer.parseInt(st.nextToken());
		curry = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<M;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 그래프 입력
		
		// 멈출 때 까지 청소하는 칸의 개수 출력
		// 0 - 청소되지 않은 칸, 1 - 벽
		while(true) {
			// 현재 칸 청소가 필요하면 청소를 한다.
			if(graph[currx][curry] == 0) {
				count+=1;
				graph[currx][curry] = 2;
			}
			
			// 상하좌우 탐색
			boolean isDurty = false;
			int nx,ny;
			for(int i=0;i<4;i++) {
				nx = currx+dx[i];
				ny = curry+dy[i];
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(graph[nx][ny] == 0) {
					isDurty = true;
					break;
				}
			}
			// 치워야할 곳이 있다면
			if(isDurty) {
				for(int i=0;i<4;i++) {
					// 반시계로 회전
					dir -= 1;
					if(dir < 0) dir+=4;
					
					nx = currx+dx[dir];
					ny = curry+dy[dir];
					if(nx<0||nx>=N||ny<0||ny>=M) continue;
					if(graph[nx][ny] == 0) {
						currx = nx;
						curry = ny;
						break;
					}
				}
			}else { // 치울 곳이 없다면 
				int back= (dir+2) % 4;
				nx = currx+dx[back];
				ny = curry+dy[back];
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(graph[nx][ny] == 1) {
					// 벽이라면 멈춘다.
					System.out.println(count);
					return;
				}
				// 후진할 수 있다면 후진한다
				currx = nx;
				curry = ny;
			}
		}
		
	}

}
