import java.util.*;
import java.io.*;

public class Main {
	/*
	 * 상근이
	 * 문서를 훔친다
	 * 문 - 열쇠 필요, 일부열쇠 가지고 있다
	 * 일부열쇠 바닥에 있다 상하좌우 움직인다
	 * 훔칠 수 있는 문서 최대개수
	 * '.'는 빈 공간을 나타낸다.
		'*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
		'$'는 상근이가 훔쳐야하는 문서이다.
		알파벳 대문자는 문을 나타낸다.
		알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
		마지막 줄에는 상근이가 이미 가지고 있는 열쇠가 공백없이 주어진다. 
		만약, 열쇠를 하나도 가지고 있지 않는 경우에는 "0"이 주어진다.
		
		달이차오른다 처럼 비트 마스킹으로 방문 처리해주려 했지만 -> 메모리 초과 오류 발생했다. 열쇠 갯수가 최대 'a' - 'z' 26개라서 
		
	 * */
	static class Gate{
		int x;
		int y;
		int idx;
		boolean isOpen;
		
	}
	static int n, m, score;
	static char graph[][], newGraph[][];
	static boolean baseKey[];
	// gate idx, 0 - 없음,모름 1-알고 있지만 아직 방문못함, 2-이미 방문
	static boolean isOpen[];
	static List<int[]> gate[];// x,y, idx, visited
	static ArrayList<int[]> start;
	static boolean key[], visit[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static ArrayDeque<int []> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			graph = new char[n+2][m+2];
			key = new boolean[26];
			gate = new ArrayList[26];
			for(int i=0;i<26;i++) {
				gate[i] = new ArrayList<int[]>();
			}
			visit = new boolean[n+2][m+2];
			score = 0;
			q = new ArrayDeque<>();

			for(int i=0;i<n+2;i++) {
				for(int j=0;j<m+2;j++) {					
					graph[i][j] = '.';
				}
			}
			
			for(int i=1;i<=n;i++) {
				String temp = in.readLine();
				for(int j=1;j<=m;j++) {
					graph[i][j] = temp.charAt(j-1);
				}
			}
			
			String keyList = in.readLine();
			if(!keyList.equals("0")) {
				for(int i=0;i<keyList.length();i++) {
					key[keyList.charAt(i)-'a'] = true;
				}
			}
		
			q.offer(new int[] {0,0});
			while(!q.isEmpty()) {
				int temp[] = q.pollFirst();
				int x=temp[0]; int y=temp[1];				
				int nx, ny;
				for(int i=0;i<4;i++) {
					nx = x+dx[i];
					ny = y+dy[i];
					if(nx<0 || nx>=n+2 || ny<0 || ny>=m+2) continue;
					if(graph[nx][ny] == '*') continue;
					if(visit[nx][ny]) continue;
					if(graph[nx][ny] >= 'A' && graph[nx][ny] <= 'Z'){
						// 문일때
						int need = graph[nx][ny] - 'A';
						if(key[need]) {
							graph[nx][ny] = '.';
							visit[nx][ny] = true;
							q.offer(new int[] {nx,ny});
						}else {
							gate[need].add(new int[] {nx,ny});
						}
					}
					if(graph[nx][ny] >= 'a' && graph[nx][ny] <= 'z'){
						// 열쇠 획득
						int thisKey = graph[nx][ny] - 'a';
						key[thisKey] = true;
						visit[nx][ny] = true;
						graph[nx][ny] = '.';
						q.offer(new int[] {nx,ny});
						if(gate[thisKey].size() > 0) {
							for(int g=0;g<gate[thisKey].size();g++) {
								int tmp[] = gate[thisKey].get(g);
								visit[tmp[0]][tmp[1]] = true;
								graph[tmp[0]][tmp[1]] = '.';
								q.offer(new int[] {tmp[0], tmp[1]});
							}							
						}
					}
					if(graph[nx][ny] == '$') {
						// 점수 증가 
						q.offer(new int[] {nx,ny});
						visit[nx][ny] = true;
						score++;
					}
					if(graph[nx][ny] == '.') {
						q.offer(new int[] {nx,ny});
						visit[nx][ny] = true;
					}
				}
			}			
			sb.append(score+"\n");
		}
		System.out.println(sb);
	}
}
