import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17143_낚시왕 {

	private static class Shark implements Comparable<Shark>{
		int speed;
		int dir;
		int weight;
		
		public Shark(int speed, int dir, int weight) {

			this.speed = speed;
			this.dir = dir;
			this.weight = weight;			
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "sp:"+speed+" weight: "+weight;
		}

		@Override
		public int compareTo(Shark o) {
			return this.weight - o.weight;
		}
	}
	static int r,c,M, res;
	static int man;
	static Shark graph[][], newGraph[][];
	
	// 1-상 2-하 3-우 4-좌
	static int dx[] = {0,-1,1,0,0};
	static int dy[] = {0,0,0,1,-1};
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new Shark[r+1][c+2];
		man = 0;
		
		int sr,sc,s,d,z;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			graph[sr][sc] = new Shark(s,d,z);
		}
		
		while(man<c+1) {
			//* 1. 낚시왕 이동(오른쪽)
			moveMan();
			//* 2. 낚시왕 있는 열에서 가장 가까운 상어 잡는다.(잡힌 상어 사라짐)
			takeShark(); 
			//* 3. 상어 이동
			moveShark();
			//* 		주어진 속도로 이동, 상어 이동하려고 하는 칸 - 경계 넘으면 방향 바꿔서 반대로 이동
			//* 		속도는 유지, 이동 마치면 한칸에 상어 여러마리 가능
		}
		System.out.println(res);
	}


	private static void moveMan() {
		man+=1;
	}


	private static void takeShark() {
		for (int i = 1; i <= r; i++) { // 가까운 상어부터 찾는다.
			if(graph[i][man] != null){ // 상어가 있다면
				res += graph[i][man].weight; // 잡은 상어 무게+
				graph[i][man] = null; // 잡은 상어 없애기
				break;
			}
		}
	}


	private static void moveShark() {
		newGraph = new Shark[r+1][c+2];
		
		for (int i = 1; i < r+1; i++) {
			for (int j = 1; j < c+2; j++) {
				if(graph[i][j] != null) {//상어가 있다면
					Shark temp = graph[i][j];
					int nx = i;
					int ny = j;

					for (int k = 0; k < temp.speed; k++) {
						nx = nx+dx[temp.dir]; // 해당 방향으로 이동
						ny = ny+dy[temp.dir];
						if(nx <= 0 || nx > r || ny <= 0 || ny >= c+1) {
							// 범위를 넘었다면 방향 전환
							if(temp.dir%2 == 0) {//짝수 -> -1
								temp.dir-=1;
							}else {
								temp.dir+=1;
							}
							// 반대로 2번 이동
							nx = nx+dx[temp.dir]*2;
							ny = ny+dy[temp.dir]*2;
						}
					}
					// 최종 이동한 좌표에 상어 추가
					if(newGraph[nx][ny] != null) {
						if(newGraph[nx][ny].weight < temp.weight) {
							newGraph[nx][ny] = new Shark(temp.speed, temp.dir, temp.weight);						
						}
					}else {
						newGraph[nx][ny] = new Shark(temp.speed, temp.dir, temp.weight);						
					}
				}
			}
		}
		
		// 움직인 후 상어 배열 복사
		for (int i = 0; i < r+1; i++) {
			graph[i] = Arrays.copyOf(newGraph[i], newGraph[i].length);
		}
	}


}
