import java.util.*;
import java.io.*;

public class Solution {
	static int n, graph[][], result, maxCnt;
	static ArrayList<int[]> process;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			result = Integer.MAX_VALUE;
			maxCnt = 0;
			n = Integer.parseInt(in.readLine());
			graph = new int[n][n];
			process = new ArrayList<>();
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0;j<n;j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					if(i!=0 && i!=n-1 && j!=0 && j!=n-1) {
						if(graph[i][j]==1) process.add(new int[] {i,j});
					}
				}
			}
			dfs(0,0,0, graph);
			if(result == Integer.MAX_VALUE) {
				result = 0;
			}
			sb.append("#"+testCase+" "+result+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int curr, int length, int cnt, int[][]board) {
		
		/*
		 * length가 더 길어도, 연결된 코어수가 더 많으면 그 길이를 써야한다.
		 * */
		
		if(cnt > maxCnt) {
			result = length;
			maxCnt = cnt;
		}else if(cnt == maxCnt) {
			result = Math.min(result, length);
		}

		if(curr >= process.size()) {
			return;
		}

		int pos[] = process.get(curr);
		int x = pos[0];
		int y = pos[1];
		

		
		for(int dir=0;dir<4;dir++) {
			int newBoard[][] = new int[n][n];
			for(int i=0;i<n;i++) {
				newBoard[i] = board[i].clone();
			}
			int nx=x, ny=y;
			int len = 0;
			// 상하좌우
			while(true) {
				len++;
				nx += dx[dir];
				ny += dy[dir];
				if(nx<0 || nx>=n || ny<0 || ny>=n) break;
				if(newBoard[nx][ny] == 1) {
					break;
				}else {
					newBoard[nx][ny] = 1;
					if(nx == 0 || nx == n-1 || ny == 0 || ny == n-1) {
						// 끝에 도착
						dfs(curr+1, len+length, cnt+1, newBoard);
					}
				}
			}	
		}
		int newBoard[][] = new int[n][n];
		for(int i=0;i<n;i++) {
			newBoard[i] = board[i].clone();
		}
		// 전원을 연결하지 않고 넘길 경우
		dfs(curr+1, length, cnt, newBoard);
	}

}
