import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_2001_파리퇴치 {

	static int N, M;
	static int [][]graph;
	static int maxKill;
	static StringBuffer sb = new StringBuffer(); 	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			graph = new int[N][N];
			maxKill = 0;
			// nxn 배열 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// mxm 크기 파리채로 최대한 많은 파리를 죽여라.
			for (int i = 0; i < N-M+1; i++) {
				for (int j = 0; j < N-M+1; j++) {
					killFly(i, j);
				}
			}
			sb.append("#").append(testCase).append(" ");
			sb.append(maxKill).append('\n');
		}
		System.out.println(sb);
	}

	private static void killFly(int x, int y) {
		int kills = 0;
		for (int i = x; i < x+M; i++) {
			for (int j = y; j < y+M; j++) {
				kills+= graph[i][j];
			}
		}
		maxKill = Math.max(maxKill, kills);
	}

}
