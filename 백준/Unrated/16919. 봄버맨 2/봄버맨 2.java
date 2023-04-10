import java.util.*;
import java.io.*;

public class Main {
	static int bx,by, bomb[][];
	static char graph[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int N,M, time;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		bomb = new int[N][M];
		graph = new char[N][M];
		
		if(time > 1) {
			time -= 2;
			time = time % 4;
			time += 2;
		}
		
		for(int i=0;i<N;i++) {
			graph[i] = in.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				if(graph[i][j] == 'O') bomb[i][j] = 3;
			}
		}// 1. 처음 폭탄설치 완료 0초

//		0초 봄버맨 초기상태 3 
//		1초 가만히 (시간 감소) 2 
		reduceBombTime();
		time--;
		if(time==0) {
			printGraph();
			return;
		}
		while(time > 0) {
//			2초 폭탄 추가설치 - 모든 칸 폭탄  1 3  
			reduceBombTime();
			setBomb();
			time--;
			if(time==0) break;
//			3초 초기 설치한 폭탄 터짐(소량) 0 2
			reduceBombTime(); 
			time--;
			if(time==0) break;
//			4초 폭탄 추가설치 - 모든 칸 폭탄 1 3
			reduceBombTime();
			setBomb();
			time--;
			if(time==0) break;
//			5초 2초에 설치한 폭탄 터짐(대량) 
			reduceBombTime();
			time--;			
			if(time==0) break;
		}

		printGraph();
	}
	private static void setBomb() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(bomb[i][j] == 0) {
					bomb[i][j] = 3;
				}
			}
		}
	}
	private static void reduceBombTime() {
		int newBomb[][] = new int[N][M];
		for(int i=0;i<N;i++) {
			newBomb[i] = Arrays.copyOf(bomb[i], bomb[i].length);
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(bomb[i][j] > 0) {
					bomb[i][j]--;
					if(newBomb[i][j] > 0) newBomb[i][j]--;
					if(bomb[i][j] == 0) {
						// 터졌다면 인접 4칸 없앰
						newBomb[i][j] = 0;
						for(int dir=0;dir<4;dir++) {
							int nx = i+dx[dir];
							int ny = j+dy[dir];
							if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
							newBomb[nx][ny] = 0;
						}
					}
				}
			}
		}
		for(int i=0;i<N;i++) {
			bomb[i] = Arrays.copyOf(newBomb[i], newBomb[i].length);
		}
	}
	private static void printGraph() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(bomb[i][j] > 0) sb.append("O");
				else sb.append(".");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
