import java.util.*;
import java.io.*;

public class Main_DANG {
	/*
	 * 격자에 무기 존재
	 * 초기 - 무기가없는 빈격자에 있다. 
	 * 초기 능력치가 있다. 
	 * 
	 * 총-공격력, 플레이어 초기 능력치 보유
	 * 플레이어번호 
	 * 
	 * 1번 플레이어부터 순차적으로 방향대로 한칸 이동. 범위 벗어나면 반대로 1이동
	 * 	**이동한 방향에 플레이어가 없다면 총이있는지 확인
	 * 	총, 총획득
	 * 	이미 총이 있다면 더 값이 큰 것을 갖고, 나머지는 해당격자에 둔다.
	 * 	**플레이어라면 초기능력치+총공격력 더 큰 플레이어 승리, 
	 * 		값이 같다면 초기능력치가 큰 플레이어 승리
	 * 		이긴 플레이어-> 포인트획득, 능력치 차이만큼 점수획득
	 * 진플레이어 -> 총을 격자에 내려놓고 원래 가지고 있던 방향으로 이동
	 * 			만약 이동칸에 다른플레이어가 있거나 범위 밖이면 90도 오른쪽 회전하며 빈칸으로 이동
	 * 		칸-총있다면, 가장 공격력 높은 총을 획득, 나머지 총 격자에 놓음
	 * 	이긴플레이어 -> 칸에 놓인 총들과 원래 총들을 비교, 가장 공격력 높은 총획득
	 * 	나머지 격자에 놓음
	 * 1번->N번 모두진행하면 1라운드 종료
	 * 각 플레이어 포인트 출력
	 * */
	static class Player{
		int id;
		int x;
		int y;
		int dir;
		int stat;
		int gun;
		public Player(int id, int x, int y, int dir, int stat, int gun) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.stat = stat;
			this.gun = gun;
		}
		
	}
	static int N, M, round, graph[][];
	static int[] point;
	static Player player[];
	static List<PriorityQueue<Integer>> gun[];
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};// ↑, →, ↓, ←
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		round = Integer.parseInt(st.nextToken());
		player = new Player[M+1];
		point = new int[M+1];
		graph = new int[N][N];
		gun = new ArrayList[N];
		for(int i=0;i<N;i++) {
			gun[i] = new ArrayList<>();
			for(int j=0;j<N;j++) {
				gun[i].add(new PriorityQueue<Integer>(Collections.reverseOrder()));
			}
		}
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<N;j++) {
				int g = Integer.parseInt(st.nextToken());
				gun[i].get(j).add(g);
				//숫자 0은 빈 칸, 0보다 큰 값은 총의 공격력
			}
		}
		
		for(int id=1;id<=M;id++) {//플레이어id
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			x--; y--;
			graph[x][y] = id;// 맵에 플레이어 위치.
			player[id] = new Player(id,x,y,d,s,0);//id,x,y,dir,stat,gun
		}
		
		for(int r=1;r<=round;r++) {
			movePlayer();
//			printGraph();
		}
		for(int i=1;i<=M;i++) {
			sb.append(point[i]+" ");
		}
		System.out.println(sb);
	}
	private static void printGraph() {
		System.out.println();
		for(int i=0;i<N;i++) {
			System.out.println(Arrays.toString(graph[i]));
		}
	}
	private static void movePlayer() {
		for(int id=1;id<=M;id++) {
			Player p1 = player[id];
			int nx = p1.x+dx[p1.dir];
			int ny = p1.y+dy[p1.dir];
			graph[p1.x][p1.y] = 0; 
			if(nx < 0 || nx>=N || ny<0 || ny>=N) {
				p1.dir = (p1.dir+2)%4;
				nx = p1.x+dx[p1.dir];
				ny = p1.y+dy[p1.dir];
			}
			p1.x = nx; p1.y = ny;
			// 플레이어 발견하면
			if(graph[nx][ny] > 0) {
				//대결시작
				fight(p1, player[graph[nx][ny]]);
			}else {
				// 좋은 총 발견하면 줍줍
				if(!gun[p1.x].get(p1.y).isEmpty()) {					
					if(gun[p1.x].get(p1.y).peek() > p1.gun) {
						int g = gun[p1.x].get(p1.y).poll();
						gun[p1.x].get(p1.y).add(p1.gun);
						p1.gun = g;
					}		
				}
				graph[p1.x][p1.y] = p1.id;
			}
		}
	}
	private static void fight(Player p1, Player p2) {
		int p1Score = p1.stat+p1.gun;
		int p2Score = p2.stat+p2.gun;
		int scoreDiff = Math.abs(p1Score-p2Score);
		graph[p1.x][p1.y] = 0;
		Player winner, loser;
		if(p1Score == p2Score) {
			if(p1.stat > p2.stat) {
				winner = p1;
				loser = p2;
			}else {
				winner = p2;
				loser = p1;
			}
		}else {
			if(p1Score > p2Score) {
				// p1승리
				winner = p1;
				loser = p2;
			}else {
				winner = p2;
				loser = p1;
			}
		}
		//진사람 총버림
		if(loser.gun > 0) {
			gun[loser.x].get(loser.y).add(loser.gun);
			loser.gun = 0;
		}
		//진사람 이동
		int lnx, lny;
		while(true) {
			lnx = loser.x+dx[loser.dir];
			lny = loser.y+dy[loser.dir];
			if(lnx < 0 || lnx>=N || lny<0 || lny>=N || graph[lnx][lny] > 0) {
				// 오른쪽 90도, 방향전환
				loser.dir = (loser.dir+1)%4;
			}else {
				loser.x=lnx;
				loser.y=lny;
				if(!gun[loser.x].get(loser.y).isEmpty()) {					
					if(gun[loser.x].get(loser.y).peek() > loser.gun) {
						int temp = loser.gun;
						loser.gun = gun[loser.x].get(loser.y).poll();
						gun[loser.x].get(loser.y).add(temp);
					}
				}
				break;
			}
			
		}
		
		//이긴사람 총줍줍
		if(gun[winner.x].get(winner.y).peek() > winner.gun) {
			int temp = winner.gun;
			winner.gun = gun[winner.x].get(winner.y).poll();
			gun[winner.x].get(winner.y).add(temp);
		}
		point[winner.id] += scoreDiff;
		graph[loser.x][loser.y] = loser.id; 
		graph[winner.x][winner.y] = winner.id;
	}

}
