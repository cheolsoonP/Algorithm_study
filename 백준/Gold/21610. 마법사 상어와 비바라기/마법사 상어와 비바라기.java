import java.util.*;
import java.io.*;

public class Main {

	static int result;
	//좌 좌상 상 우상 우 우하 하 좌하 
	static int dx[] = {0,0,-1,-1,-1,0,1,1,1};
	static int dy[] = {0,-1,-1,0,1,1,1,0,-1};
	static int map[][];
	static int currCloud[][];
	static int nextCloud[][];
	static int N,M;
	static int d; 
	static int s;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		currCloud = new int[N][N];
		nextCloud = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기 구름 생성
		currCloud[N-1][0] = 1;
		currCloud[N-1][1] = 1;
		currCloud[N-2][0] = 1;
		currCloud[N-2][1] = 1;
		
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());

			// 1. 모든 구름 di 방향으로 si칸 이동
			// 2. 구름에서 비가 내려 구름이 있는 칸의 물의 양 1 증가
			// 3. 구름 사라진다.
			play();
			
			// 4. 물이 증가한 칸에 물복사 마법 사용. -> 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼
			// r,c에 바구니의 물의양 증가 (2,2)의 대각선에 물이 있는 바구니가 3개 있으면 3증가
			// (이때는 경계를 넘는 칸은 체크하지 않음.)
			waterMagic();
			
			// 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름 생성. 물의양 -2 
			// (구름이 새로 생성되는 칸은 이전에 구름이 사라진 자리이면 안된다.)
			makeCloud();
		}
		
		checkResult();
		System.out.println(result);
	}


	private static void checkResult() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				result += map[i][j];
			}
		}
	}


	private static void makeCloud() {
		// 구름 생성, 기존에 있던 구름 흔적 확인
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] >= 2 && nextCloud[i][j] != 1) {
					currCloud[i][j] = 1; // 구름생성
					map[i][j] -= 2;
				}
			}
		}
		
		// 흔적 지우기
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(nextCloud[i][j] == 1) nextCloud[i][j] = 0;
			}
		}
	}


	private static void play() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(currCloud[i][j] == 1) {
					// 구름이 있었다면 다음 위치 찾아서 옮기기, 기존 위치는 0으로 바꾸기. 
					int x = i;
					int y = j;
					int nx = x+dx[d]*s;
					int ny = y+dy[d]*s;
					
					// 행, 열 넘어갈 때 처리(행, 열 연결됨)
					if(nx < 0) nx = N - (Math.abs(nx) % N);
					nx = nx % N;
					if(ny < 0) ny = N - (Math.abs(ny) % N);
					ny = ny % N;
					
					// 1. 구름 이동
					currCloud[x][y] = 0;
					nextCloud[nx][ny] = 1;

					// 2. 비 내림.
					map[nx][ny] += 1;
					
				}
			}
		}
	}
	

	private static void waterMagic() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(nextCloud[i][j] == 1) {
					// 대각선 체크 
					int count = 0;
					for(int dir = 2;dir<=8;dir+=2) {
						int nnx = i+dx[dir];
						int nny = j+dy[dir];
						if(nnx<0||nnx>=N||nny<0||nny>=N) continue;
						if(map[nnx][nny] > 0) count++;
					}
					map[i][j] += count;
				}
			}
		}
	}

}
